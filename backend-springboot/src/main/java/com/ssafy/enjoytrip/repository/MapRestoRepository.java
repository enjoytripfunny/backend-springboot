package com.ssafy.enjoytrip.repository;

import java.util.List;
import java.util.Map;

import com.ssafy.enjoytrip.dto.MapRestoDto;

public interface MapRestoRepository {
	void makeMapResto(MapRestoDto mapResto) throws Exception;
	String getResto(String restoApiId) throws Exception;
	void updateSaveCount(String restoApiId) throws Exception;
	void registerRestos(MapRestoDto mapResto) throws Exception;
	void registerFile(MapRestoDto mapResto) throws Exception;
	void registerUserResto(MapRestoDto mapResto) throws Exception;
	List<MapRestoDto> getMapRestosList(Map<String, String> param) throws Exception;
}
