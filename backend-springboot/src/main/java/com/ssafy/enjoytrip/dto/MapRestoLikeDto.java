package com.ssafy.enjoytrip.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
// 맛지도에 대한 dto
public class MapRestoLikeDto {
	private String mapRestoNo; //맛지도 인덱스
	private String userId; //맛지도 만든 사용자 아이디
	private String subject; //맛지도 제목
//	private String content; //맛지도 간단 설명
	private FileInfoDto fileInfo; //맛지도 썸네일
//	private List<RestoDto> restos; //맛지도안에 맛집들의 api 아이디
//	private List<String> tags; //맛지도에 대한 태그
	private String registerTime; //맛지도 만들어진 날짜
	private String likeValue;
	private String likeCount;
}
