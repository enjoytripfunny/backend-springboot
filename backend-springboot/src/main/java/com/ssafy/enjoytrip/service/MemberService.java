package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.dto.MemberDto;

import java.sql.SQLException;
import java.util.*;

public interface MemberService {
    MemberDto login(MemberDto memberDto) throws Exception;
    MemberDto userInfo(String userId) throws Exception;
    void saveRefreshToken(String userId, String refreshToken) throws SQLException;
    int idCheck(String userId) throws Exception;
    void signup(MemberDto memberDto) throws Exception;
    void memberModify(MemberDto memberDto) throws Exception;
    void memberDelete(String userId) throws Exception;
    void deleRefreshToken(String userId) throws Exception;
}
