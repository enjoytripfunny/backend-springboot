package com.ssafy.enjoytrip.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.enjoytrip.dto.TripBoardDto;

@Repository("TripBoardRepositoryImpl")
public class TripBoardRepositoryImpl implements TripBoardRepository{
	
	SqlSession session;
	final String NAMESPACE = "com.ssafy.enjoytrip.repository.TripBoardRepository.";
	
	@Autowired
	public TripBoardRepositoryImpl(SqlSession session) {
		super();
		this.session = session;
	}

	@Override
	public void writeArticle(TripBoardDto tripDto) throws Exception {
		session.insert(NAMESPACE + "writeArticle", tripDto);
	}

	@Override
	public List<TripBoardDto> listArticle(Map<String, Object> param) throws Exception {
		return session.selectList(NAMESPACE + "listArticle", param);
	}

	@Override
	public int getTotalArticleCount(Map<String, String> param) throws Exception {
		return session.selectOne(NAMESPACE + "getTotalArticleCount", param);
	}

	@Override
	public TripBoardDto getArticle(int articleNo) throws Exception {
		return session.selectOne(NAMESPACE + "getArticle", articleNo);
	}

	@Override
	public void updateHit(int articleNo) throws Exception {
		session.update(NAMESPACE + "updateHit", articleNo);
	}

	@Override
	public void modifyArticle(TripBoardDto tripDto) throws Exception {
		session.update(NAMESPACE + "modifyArticle", tripDto);
	}

	@Override
	public void deleteArticle(int articleNo) throws Exception {
		session.delete(NAMESPACE + "deleteArticle", articleNo);
	}

}
