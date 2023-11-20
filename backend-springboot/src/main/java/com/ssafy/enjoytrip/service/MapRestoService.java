package com.ssafy.enjoytrip.service;

import java.util.List;

import com.ssafy.enjoytrip.dto.FileInfoDto;
import com.ssafy.enjoytrip.dto.MapRestoDto;
import com.ssafy.enjoytrip.dto.MapRestoLikeDto;
import com.ssafy.enjoytrip.dto.MapRestoMypageDto;

/*
 * 1. map_restaurant 테이블에  makeMapResto sql 써서 맛지도 등록
 * 	1.1 for문으로 이미 등록된 맛집인지 getResto확인하고
 * 		1.1.1  이미 등록된 맛집이라면 updateSaveCount
 * 		1.1.2  등록되지 않은 맛집이라면 restaurant 테이블에 새로 등록 registerRestos
 * 	1.2. registerFile로 파일 등록
 * 2. 맛집 지도에 저장된 맛집을 user_restaurant 테이블에 registerUserResto 써서 저장
 * 	
 */
public interface MapRestoService {
	void makeMapResto(MapRestoDto mapResto) throws Exception;
	List<MapRestoLikeDto> getMapRestosList(int num, int totalMap) throws Exception;
//	List<MapRestoDto> getMapRestosList(int num) throws Exception;
	void registerFileTest(FileInfoDto file) throws Exception;
	int getTotalMapResto() throws Exception;
	List<MapRestoMypageDto> getMyMapResto(String userId) throws Exception;
	List<MapRestoMypageDto> getLikeMapResto(String userId) throws Exception;
}
