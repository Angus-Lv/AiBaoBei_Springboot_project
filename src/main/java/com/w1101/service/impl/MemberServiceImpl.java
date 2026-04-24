package com.w1101.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.w1101.entity.Member;
import com.w1101.mapper.MemberMapper;
import com.w1101.service.MemberService;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Override
    public IPage<Member> getMemberList(int page, int pageSize, String keyword, String status) {
        Page<Member> pageInfo = new Page<>(page, pageSize);

        LambdaQueryWrapper<Member> wrapper = new LambdaQueryWrapper<>();

        // 关键词搜索（用户名、昵称、手机号）
        if (StringUtils.isNotBlank(keyword)) {
            wrapper.like(Member::getUserName, keyword)
                    .or().like(Member::getNickname, keyword)
                    .or().like(Member::getPhone, keyword);
        }

        // 状态筛选
        if (StringUtils.isNotBlank(status)) {
            wrapper.eq(Member::getStatus, status);
        }

        // 按创建时间倒序
        wrapper.orderByDesc(Member::getCreateTime);

        return this.page(pageInfo, wrapper);
    }

    @Override
    public Member getMemberById(Integer id) {
        return this.getById(id);
    }

    @Override
    public boolean updateMember(Member member) {
        return this.updateById(member);
    }

    @Override
    public boolean updateMemberStatus(Integer id, String status) {
        Member member = new Member();
        member.setId(id);
        member.setStatus(status);
        return this.updateById(member);
    }
}
