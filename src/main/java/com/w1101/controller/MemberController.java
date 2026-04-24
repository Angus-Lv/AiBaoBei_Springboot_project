package com.w1101.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.w1101.entity.Member;
import com.w1101.service.MemberService;
import com.w1101.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/list")
    public Map<String, Object> getMemberList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String keyword) {
        IPage<Member> memberPage = memberService.getMemberList(page, pageSize, keyword, null);

        Map<String, Object> data = new HashMap<>();
        data.put("list", memberPage.getRecords());
        data.put("total", memberPage.getTotal());

        return ResponseUtil.success("success", data);
    }

    @GetMapping("/{id}")
    public Map<String, Object> getMemberById(@PathVariable Integer id) {
        Member member = memberService.getMemberById(id);
        if (member == null) {
            return ResponseUtil.notFound();
        }
        return ResponseUtil.success("获取成功", member);
    }

    @PutMapping("/{id}")
    public Map<String, Object> updateMember(@PathVariable Integer id, @RequestBody Map<String, Object> memberData) {
        Integer vipLevel = (Integer) memberData.get("vipLevel");
        Integer points = (Integer) memberData.get("points");

        Member member = new Member();
        member.setId(id);
        member.setVipLevel(vipLevel != null ? vipLevel.toString() : null);
        member.setPoints(points);

        boolean success = memberService.updateMember(member);
        if (success) {
            return ResponseUtil.success("success");
        }
        return ResponseUtil.error("更新失败");
    }

    @PutMapping("/{id}/status")
    public Map<String, Object> updateMemberStatus(@PathVariable Integer id, @RequestBody Map<String, String> statusData) {
        String status = statusData.get("status");
        if (status == null) {
            return ResponseUtil.error("状态参数不能为空");
        }

        boolean success = memberService.updateMemberStatus(id, status);
        if (success) {
            Map<String, Object> data = new HashMap<>();
            data.put("id", id);
            data.put("status", status);
            return ResponseUtil.success("操作成功", data);
        }
        return ResponseUtil.error("操作失败");
    }
}
