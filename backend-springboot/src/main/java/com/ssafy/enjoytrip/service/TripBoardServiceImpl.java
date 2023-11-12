package com.ssafy.enjoytrip.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.dto.TripBoardDto;
import com.ssafy.enjoytrip.repository.TripBoardRepository;
import com.ssafy.util.SizeConstant;

@Service("TripBoardServiceImpl")
public class TripBoardServiceImpl implements TripBoardServcie {
	
	SqlSession session;
	
	@Autowired
	public TripBoardServiceImpl(SqlSession session) {
		super();
		this.session = session;
	}

	@Override
	public void writeArticle(TripBoardDto tripDto) throws Exception {
		session.getMapper(TripBoardRepository.class).writeArticle(tripDto);
	}

	@Override
	public List<TripBoardDto> listArticle(Map<String, String> map) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		String key = map.get("key");
		if("userid".equals(key))
			key = "b.user_id";
		param.put("key", key == null ? "" : key);
		param.put("word", map.get("word") == null ? "" : map.get("word"));
		int pgNo = Integer.parseInt(map.get("pgno") == null ? "1" : map.get("pgno"));
		int start = pgNo * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
		param.put("start", start);
		param.put("listsize", SizeConstant.LIST_SIZE);
		return session.getMapper(TripBoardRepository.class).listArticle(param);
	}

	@Override
	public TripBoardDto getArticle(int articleNo) throws Exception {
		return session.getMapper(TripBoardRepository.class).getArticle(articleNo);
	}

	@Override
	public void updateHit(int articleNo) throws Exception {
		session.getMapper(TripBoardRepository.class).updateHit(articleNo);

	}

	@Override
	public void modifyArticle(TripBoardDto tripDto) throws Exception {
		session.getMapper(TripBoardRepository.class).modifyArticle(tripDto);
	}

	@Override
	public void deleteArticle(int articleNo) throws Exception {
//		sqlSession.getMapper(CommentRepository.class).deletesComment(articleNo);
		session.getMapper(TripBoardRepository.class).deleteArticle(articleNo);
	}

	@Override
	public int getTotalArticleCount(Map<String, String> param) throws Exception {
		return session.getMapper(TripBoardRepository.class).getTotalArticleCount(param);
	}

//	@Override
//	public PageNavigation makePageNavigation(Map<String, String> map) throws Exception {
//		PageNavigation pageNavigation = new PageNavigation();
//
//		int naviSize = SizeConstant.NAVIGATION_SIZE;
//		int sizePerPage = SizeConstant.LIST_SIZE;
//		int currentPage = Integer.parseInt(map.get("pgno"));
//
//		pageNavigation.setCurrentPage(currentPage);
//		pageNavigation.setNaviSize(naviSize);
//		Map<String, Object> param = new HashMap<String, Object>();
//		String key = map.get("key");
//		param.put("key", key.isEmpty() ? "" : key);
//		param.put("word", map.get("word").isEmpty() ? "" : map.get("word"));
//		
//		int totalCount = session.getMapper(TripBoardRepository.class).getTotalArticleCount(param);
//		pageNavigation.setTotalCount(totalCount);
//		int totalPageCount = (totalCount - 1) / sizePerPage + 1;
//		pageNavigation.setTotalPageCount(totalPageCount);
//		boolean startRange = currentPage <= naviSize;
//		pageNavigation.setStartRange(startRange);
//		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;
//		pageNavigation.setEndRange(endRange);
//		pageNavigation.makeNavigator();
//
//		return pageNavigation;
//	}

}
