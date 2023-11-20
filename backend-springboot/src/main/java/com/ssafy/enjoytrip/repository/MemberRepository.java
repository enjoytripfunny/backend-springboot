package com.ssafy.enjoytrip.repository;

import com.ssafy.enjoytrip.dto.MemberDto;

import java.sql.SQLException;
import java.util.*;
public interface MemberRepository {
    MemberDto login(MemberDto memberDto) throws Exception;
    MemberDto userInfo(String userId) throws Exception;
    void saveRefreshToken(Map<String, String> map) throws SQLException;
    int idCheck(String userId) throws Exception;
    void signup(MemberDto memberDto) throws Exception;
    void memberModify(MemberDto memberDto) throws Exception;
    void memberDelete(String userId) throws Exception;
}
