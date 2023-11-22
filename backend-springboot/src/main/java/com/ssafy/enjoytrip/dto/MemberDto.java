package com.ssafy.enjoytrip.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDto {
    private String userId;
    private String userName;
    private String userPassword;
    private String emailId;
    private String emailDomain;
    private String token;
    private String joinDate;
    private String refreshToken;
}
