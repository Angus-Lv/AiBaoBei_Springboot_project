package com.w1101.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.w1101.entity.Member;

public interface MemberService {

    IPage<Member> getMemberList(int page, int pageSize, String keyword, String status);

    Member getMemberById(Integer id);

    boolean updateMember(Member member);

    boolean updateMemberStatus(Integer id, String status);
}
