package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.dto.MemberDto;
import com.ssafy.enjoytrip.repository.MemberRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

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
        System.out.println("service >> " + memberDto);

        MemberDto login = session.getMapper(MemberRepository.class).login(memberDto);
        System.out.println("service login: " +login);
        return login;
    }

    @Override
    public MemberDto userInfo(String userId) throws Exception {
        return session.getMapper(MemberRepository.class).userInfo(userId);
    }

    @Override
    public void saveRefreshToken(String userId, String refreshToken) throws SQLException {
        System.out.println("refresh token !!!");
        Map<String, String> map = new HashMap<String, String>();
        map.put("userId", userId);
        map.put("token", refreshToken);
        session.getMapper(MemberRepository.class).saveRefreshToken(map);
    }

    @Override
    public int idCheck(String userId) throws Exception {
        return session.getMapper(MemberRepository.class).idCheck(userId);
    }

    @Override
    public void signup(MemberDto memberDto) throws Exception {
        session.getMapper(MemberRepository.class).signup(memberDto);
    }

    @Override
    public void memberModify(MemberDto memberDto) throws Exception {
        session.getMapper(MemberRepository.class).memberModify(memberDto);
    }

    @Override
    public void memberDelete(String userId) throws Exception {
        session.getMapper(MemberRepository.class).memberDelete(userId);
    }
}
