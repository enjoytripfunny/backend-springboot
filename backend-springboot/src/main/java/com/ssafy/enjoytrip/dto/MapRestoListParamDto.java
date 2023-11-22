package com.ssafy.enjoytrip.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MapRestoListParamDto {
	private int num; //페이지 번호
	private int total; //전체 페이지 수
	private String userId; //사용자 아이디
}
