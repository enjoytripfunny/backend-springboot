package com.ssafy.enjoytrip.service;

import java.util.List;
import java.util.Map;

import com.ssafy.enjoytrip.dto.TripBoardDto;

public interface TripBoardServcie {
	void writeArticle(TripBoardDto tripDto) throws Exception;
	List<TripBoardDto> listArticle(Map<String, String> map) throws Exception;
//	PageNavigation makePageNavigation(Map<String, String> map) throws Exception;
	int getTotalArticleCount(Map<String, String> param) throws Exception;
	TripBoardDto getArticle(int articleNo) throws Exception;
	void updateHit(int articleNo) throws Exception;
	void modifyArticle(TripBoardDto tripDto) throws Exception;
	void deleteArticle(int articleNo) throws Exception;
}
