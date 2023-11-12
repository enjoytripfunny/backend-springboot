package com.ssafy.enjoytrip.repository;

import com.ssafy.enjoytrip.dto.MemberDto;

public interface MemberRepository {
    MemberDto login(MemberDto memberDto) throws Exception;
    void memberDelete(String userId) throws Exception;
}
