package com.ssafy.enjoytrip.repository;

import java.util.List;
import java.util.Map;

import com.ssafy.enjoytrip.dto.FileInfoDto;
import com.ssafy.enjoytrip.dto.MapRestoDto;
import com.ssafy.enjoytrip.dto.MapRestoLikeDto;
import com.ssafy.enjoytrip.dto.RestoDto;

public interface MapRestoRepository {
	void makeMapResto(MapRestoDto mapResto) throws Exception;
	String getResto(String restoApiId) throws Exception;
	void updateSaveCount(String restoApiId) throws Exception;
	void registerResto(RestoDto resto) throws Exception;
	void registerRestos(MapRestoDto mapResto) throws Exception;
	void registerFile(MapRestoDto mapResto) throws Exception;
	void registerFileTest(FileInfoDto file) throws Exception;
	void registerUserResto(MapRestoDto mapResto) throws Exception;
	List<MapRestoLikeDto> getMapRestosList(Map<String, Object> param) throws Exception;
	List<MapRestoLikeDto> getMapRestosLikeList(Map<String, Object> param) throws Exception;
//	List<MapRestoDto> getMapRestosList(Map<String, Object> param) throws Exception;
	FileInfoDto getFileInfo(String mapRestoNo) throws Exception;
	int getTotalMapResto() throws Exception;
}
