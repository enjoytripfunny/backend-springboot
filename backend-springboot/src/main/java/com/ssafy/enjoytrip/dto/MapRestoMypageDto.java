package com.ssafy.enjoytrip.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MapRestoMypageDto {
	private String mapRestoNo; //맛지도 인덱스
	private String userId; //맛지도 만든 사용자 아이디
	private String subject; //맛지도 제목
	private FileInfoDto fileInfo; //맛지도 썸네일
//	private String registerTime; //맛지도 만들어진 날짜
}
