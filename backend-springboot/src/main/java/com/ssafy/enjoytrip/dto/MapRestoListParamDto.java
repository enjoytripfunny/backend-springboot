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
	private String checkUserId; //나의 맛지도만 불러오기
	private String orderkey; //정렬할 키
	private String ordervalue; //내림차순, 오름차순
}
