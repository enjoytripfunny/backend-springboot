package com.ssafy.enjoytrip.repository;

import com.ssafy.enjoytrip.dto.MemberDto;
import com.ssafy.enjoytrip.dto.TripBoardDto;

public interface MemberRepository {
    MemberDto login(MemberDto memberDto) throws Exception;
    void memberModify(MemberDto memberDto) throws Exception;

}
