package com.ssafy.enjoytrip.repository;

import java.util.List;
import java.util.Map;

import com.ssafy.enjoytrip.dto.FileInfoDto;
import com.ssafy.enjoytrip.dto.LikeInfoDto;
import com.ssafy.enjoytrip.dto.MapRestoDto;
import com.ssafy.enjoytrip.dto.MapRestoLikeDto;
import com.ssafy.enjoytrip.dto.MapRestoListParamDto;
import com.ssafy.enjoytrip.dto.MapRestoMypageDto;
import com.ssafy.enjoytrip.dto.RestoDto;

public interface MapRestoRepository {
	void makeMapResto(MapRestoDto mapResto) throws Exception; // 맛지도 생성
	String getResto(String restoApiId) throws Exception;
	void updateSaveCount(String restoApiId) throws Exception; 
	void registerResto(RestoDto resto) throws Exception; //맛지도에서 맛집 하나씩 등록
	void registerRestos(MapRestoDto mapResto) throws Exception; //맛지도에서 맛집 한번에 등록 -> 이건 사용x
	void registerFile(MapRestoDto mapResto) throws Exception; // 파일 저장하기
	void registerFileTest(FileInfoDto file) throws Exception; //파일 업로드 테스트용
	void registerUserResto(MapRestoDto mapResto) throws Exception; // 내 맛지도에 저장한 식당 저장하기
	List<MapRestoLikeDto> getMapRestosList(Map<String, Object> param) throws Exception; //좋아요 값 제외 맛지도 목록 불러오기
	List<MapRestoLikeDto> getMapRestosLikeList(MapRestoListParamDto param) throws Exception; //좋아요 포함 맛지도 목록 불러오기
	FileInfoDto getFileInfo(String mapRestoNo) throws Exception; // 파일 정보 불러오기
	int getTotalMapResto(String checkUserId) throws Exception; // 맛지도 목록 개수
	List<MapRestoMypageDto> getMyMapResto(Map<String, Object> param) throws Exception; // 내가 작성한 맛지도 불러오기
	List<MapRestoMypageDto> getLikeMapResto(Map<String, Object> param) throws Exception; // 좋아요한 맛지도 불러오기
	MapRestoDto getDetailMapResto(String mapRestoNo) throws Exception; // 맛지도 상세보기
	void registerTags(MapRestoDto mapResto) throws Exception; // 태그 등록
	int getTotalMyMapResto(String userId) throws Exception; // 내가 작성한 맛지도 총개수
	int getTotalLikeMapResto(String userId) throws Exception; // 좋아요한 맛지도 총개수
	List<RestoDto> getUserRestoList(String mapRestoNo) throws Exception; // 맛지도에 등록된 식당들 가져오기
	void changeLike(LikeInfoDto likeInfo) throws Exception; // 좋아요 누르기
	void updateLikeMapResto(LikeInfoDto likeInfo) throws Exception; // 식당의 좋아요 count 변경
	List<MapRestoLikeDto> getFiterMyMapResto(MapRestoListParamDto param) throws Exception; // 필터링해서 맛지도 리스트 가져오기
	List<RestoDto> getAllResto() throws Exception; // 전체 맛집 들고오기
	List<RestoDto> getTagResto(String tag) throws Exception; // 태그별 맛집 들고오기
}
