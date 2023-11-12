package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.dto.MemberDto;
import com.ssafy.enjoytrip.repository.MemberRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("MemberServiceImpl")
public class MemberServiceImpl implements MemberService {
    SqlSession session;
    @Autowired
    public MemberServiceImpl(SqlSession session) {
        super();
        this.session = session;
    }
    @Override
    public MemberDto login(MemberDto memberDto) throws Exception {
        System.out.println("service >>" + memberDto);
        MemberDto test = session.getMapper(MemberRepository.class).login(memberDto);
        System.out.println("test >>" + test);
        return test;
    }

    @Override
    public void memberDelete(String userId) throws Exception {
        session.getMapper(MemberRepository.class).memberDelete(userId);
    }
}
