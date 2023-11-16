package com.ssafy.enjoytrip.repository;

import com.ssafy.enjoytrip.dto.MapRestoDto;

public interface MapRestoRepository {
	void makeMapResto(MapRestoDto mapResto) throws Exception;
	String getResto(String restoApiId) throws Exception;
	void updateSaveCount(String restoApiId) throws Exception;
	void registerRestos(MapRestoDto mapResto) throws Exception;
	void registerFile(MapRestoDto mapResto) throws Exception;
	void registerUserResto(MapRestoDto mapResto) throws Exception;
}
