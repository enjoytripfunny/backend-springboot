package com.ssafy.enjoytrip.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Data
//음식점에 대한 dto
public class RestoDto {
//	private int restoNo; //음식점 인덱스
	private String restoApiId; //음식점 api아이디
	private String restoName; //음식점 이름
	private String restoPhone; //음식점 전화번호
	private String category; //음식점 카테고리
	private String address; //음식점 주소
	private String latitude; //음식점 위도
	private String longitude; //음식점 경도
//	private String likeCount; //좋아요개수
//	private String totalScore; //총 점수
	private String saveCount; //음식점 저장한 사용자 수
	
}
