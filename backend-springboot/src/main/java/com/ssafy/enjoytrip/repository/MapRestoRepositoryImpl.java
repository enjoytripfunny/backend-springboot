package com.ssafy.enjoytrip.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.enjoytrip.dto.FileInfoDto;
import com.ssafy.enjoytrip.dto.MapRestoDto;
import com.ssafy.enjoytrip.dto.MapRestoLikeDto;
import com.ssafy.enjoytrip.dto.RestoDto;

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
	public List<MapRestoLikeDto> getMapRestosList(Map<String, Object> param) throws Exception {
//		return session.selectList(NAMESPACE + "getMapRestoFileList", param);
//		return session.selectList(NAMESPACE + "getMapRestosList", param);
		return session.selectList(NAMESPACE + "getMapRestosLikeList", param);
	}

	@Override
	public void registerFileTest(FileInfoDto file) throws Exception {
		session.insert(NAMESPACE + "registerFileTest", file);
	}

	@Override
	public FileInfoDto getFileInfo(String mapRestoNo) throws Exception {
		return session.selectOne(NAMESPACE + "getFileInfo", mapRestoNo);
	}

	@Override
	public void registerResto(RestoDto resto) throws Exception {
		session.insert(NAMESPACE + "registerResto", resto);
	}

}
