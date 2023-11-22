package com.ssafy.enjoytrip.service;

import java.util.List;
import java.util.Map;

import com.ssafy.enjoytrip.dto.FileInfoDto;
import com.ssafy.enjoytrip.dto.LikeInfoDto;
import com.ssafy.enjoytrip.dto.MapRestoDto;
import com.ssafy.enjoytrip.dto.MapRestoLikeDto;
import com.ssafy.enjoytrip.dto.MapRestoListParamDto;
import com.ssafy.enjoytrip.dto.MapRestoMypageDto;
import com.ssafy.enjoytrip.dto.RestoDto;

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
	List<MapRestoLikeDto> getMapRestosList(MapRestoListParamDto param) throws Exception;
//	List<MapRestoDto> getMapRestosList(int num) throws Exception;
	void registerFileTest(FileInfoDto file) throws Exception;
	int getTotalMapResto() throws Exception;
	List<MapRestoMypageDto> getMyMapResto(Map<String, Object> map) throws Exception;
	List<MapRestoMypageDto> getLikeMapResto(Map<String, Object> map) throws Exception;
	MapRestoDto getDetailMapResto(String mapRestoNo) throws Exception;
	int getTotalMyMapResto(String userId) throws Exception; // 내가 작성한 맛지도 총개수
	int getTotalLikeMapResto(String userId) throws Exception; // 좋아요한 맛지도 총개수
	List<RestoDto> getUserRestoList(String mapRestoNo) throws Exception; // 맛지도에 등록된 식당들 가져오기
	void changeLike(LikeInfoDto likeInfo) throws Exception;
}
