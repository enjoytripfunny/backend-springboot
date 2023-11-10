package com.ssafy.enjoytrip.repository;

import java.util.List;
import java.util.Map;

import com.ssafy.enjoytrip.dto.TripBoardDto;

public interface TripBoardRepository {
	void writeArticle(TripBoardDto tripDto) throws Exception;
	List<TripBoardDto> listArticle(Map<String, Object> param) throws Exception;
	int getTotalArticleCount(Map<String, Object> param) throws Exception;
	TripBoardDto getArticle(int articleNo) throws Exception;
	void updateHit(int articleNo) throws Exception;
	void modifyArticle(TripBoardDto tripDto) throws Exception;
	void deleteArticle(int articleNo) throws Exception;
}
