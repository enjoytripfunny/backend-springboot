package com.ssafy.enjoytrip.repository;

import java.util.List;
import java.util.Map;

import com.ssafy.enjoytrip.dto.FileInfoDto;
import com.ssafy.enjoytrip.dto.MapRestoDto;
import com.ssafy.enjoytrip.dto.MapRestoLikeDto;
import com.ssafy.enjoytrip.dto.MapRestoMypageDto;
import com.ssafy.enjoytrip.dto.RestoDto;

public interface MapRestoRepository {
	void makeMapResto(MapRestoDto mapResto) throws Exception;
	String getResto(String restoApiId) throws Exception;
	void updateSaveCount(String restoApiId) throws Exception;
	void registerResto(RestoDto resto) throws Exception; //맛지도에서 맛집 하나씩 등록
	void registerRestos(MapRestoDto mapResto) throws Exception; //맛지도에서 맛집 한번에 등록 -> 이건 사용x
	void registerFile(MapRestoDto mapResto) throws Exception;
	void registerFileTest(FileInfoDto file) throws Exception; //파일 업로드 테스트용
	void registerUserResto(MapRestoDto mapResto) throws Exception;
	List<MapRestoLikeDto> getMapRestosList(Map<String, Object> param) throws Exception; //좋아요 값 제외 맛지도 목록 불러오기
	List<MapRestoLikeDto> getMapRestosLikeList(Map<String, Object> param) throws Exception; //좋아요 포함 맛지도 목록 불러오기
	FileInfoDto getFileInfo(String mapRestoNo) throws Exception;
	int getTotalMapResto() throws Exception; // 맛지도 목록 개수
	List<MapRestoMypageDto> getMyMapResto(String userId) throws Exception;
	List<MapRestoMypageDto> getLikeMapResto(String userId) throws Exception;
	MapRestoDto getDetailMapResto(String mapRestoNo) throws Exception;
	void registerTags(MapRestoDto mapResto) throws Exception;
}
