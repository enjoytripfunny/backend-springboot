package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.dto.MemberDto;

public interface MemberService {
    MemberDto login(MemberDto memberDto) throws Exception;
}
