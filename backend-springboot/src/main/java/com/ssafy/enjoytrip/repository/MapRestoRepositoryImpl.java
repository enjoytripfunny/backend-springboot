package com.ssafy.enjoytrip.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.enjoytrip.dto.MapRestoDto;

@Repository("MapRestoRepositoryImpl")
public class MapRestoRepositoryImpl implements MapRestoRepository {
	
	SqlSession session;
	final String NAMESPACE = "com.ssafy.enjoytrip.repository.MapRestoRepository.";
	
	@Autowired
	public MapRestoRepositoryImpl(SqlSession session) {
		super();
		this.session = session;
	}

	@Override
	public void makeMapResto(MapRestoDto mapResto) throws Exception {
		session.insert(NAMESPACE + "makeMapResto", mapResto);
	}

	@Override
	public String getResto(String restoApiId) throws Exception {
		return session.selectOne(NAMESPACE + "getResto", restoApiId);
	}

	@Override
	public void updateSaveCount(String restoApiId) throws Exception {
		session.update(NAMESPACE + "updateSaveCount", restoApiId);
	}

	@Override
	public void registerRestos(MapRestoDto mapResto) throws Exception {
		session.insert(NAMESPACE + "registerRestos", mapResto);
	}

	@Override
	public void registerFile(MapRestoDto mapResto) throws Exception {
		session.insert(NAMESPACE + "registerFile", mapResto);
	}

	@Override
	public void registerUserResto(MapRestoDto mapResto) throws Exception {
		session.insert(NAMESPACE + "registerUserResto", mapResto);
	}

	@Override
	public List<MapRestoDto> getMapRestosList(Map<String, String> param) throws Exception {
		return session.selectList(NAMESPACE + "getMapRestosList", param);
	}

}
