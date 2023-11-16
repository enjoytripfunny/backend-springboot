package com.ssafy.enjoytrip.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//사용자의 지도에 저장된 맛집
public class UserResto {
//	private int userRestoNo; //저장된 음식점 인덱스
//	private int mapRestoNo; //맛지도 인덱스
	private String restoApiId; //음식점 api아이디
	
}
