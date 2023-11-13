package com.ssafy.enjoytrip.repository;

import com.ssafy.enjoytrip.dto.MemberDto;

public interface MemberRepository {
    MemberDto login(MemberDto memberDto) throws Exception;
    int idCheck(String userId) throws Exception;
    void signup(MemberDto memberDto) throws Exception;
    void memberModify(MemberDto memberDto) throws Exception;

}
