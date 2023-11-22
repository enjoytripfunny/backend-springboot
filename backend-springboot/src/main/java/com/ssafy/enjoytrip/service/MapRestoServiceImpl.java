package com.ssafy.enjoytrip.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.enjoytrip.dto.FileInfoDto;
import com.ssafy.enjoytrip.dto.LikeInfoDto;
import com.ssafy.enjoytrip.dto.MapRestoDto;
import com.ssafy.enjoytrip.dto.MapRestoLikeDto;
import com.ssafy.enjoytrip.dto.MapRestoListParamDto;
import com.ssafy.enjoytrip.dto.MapRestoMypageDto;
import com.ssafy.enjoytrip.dto.RestoDto;
import com.ssafy.enjoytrip.repository.MapRestoRepository;

import lombok.extern.slf4j.Slf4j;

/*
 * 1. map_restaurant 테이블에  makeMapResto sql 써서 맛지도 등록
 * 	1.1 for문으로 이미 등록된 맛집인지 getResto확인하고
 * 		1.1.1  이미 등록된 맛집이라면 updateSaveCount
 * 		1.1.2  등록되지 않은 맛집이라면 restaurant 테이블에 새로 등록 registerRestos
 * 	1.2. registerFile로 파일 등록
 * 2. 맛집 지도에 저장된 맛집을 user_restaurant 테이블에 registerUserResto 써서 저장
 * 	
 */
@Service("MapRestoServiceImpl")
@Slf4j
public class MapRestoServiceImpl implements MapRestoService {
	
	SqlSession session;
	
	@Autowired
	public MapRestoServiceImpl(SqlSession session) {
		super();
		this.session = session;
	}

	@Override
	@Transactional
	public void makeMapResto(MapRestoDto mapResto) throws Exception {
		// 맛지도 만들기
		System.out.println("mapResto 등록 전: " + mapResto.getMapRestoNo());
		session.getMapper(MapRestoRepository.class).makeMapResto(mapResto);
		System.out.println("mapResto 등록 후: " + mapResto.getMapRestoNo());
		// 파일 등록
		session.getMapper(MapRestoRepository.class).registerFile(mapResto);
		System.out.println("파일 등록 완료");
		// 맛집 등록
		System.out.println("mapResto.getRestos(): " + mapResto.getRestos());
		if (mapResto.getRestos() != null ) {
			for (int i = 0; i < mapResto.getRestos().length; i++) {
				session.getMapper(MapRestoRepository.class).registerResto(mapResto.getRestos()[i]);
			}
			session.getMapper(MapRestoRepository.class).registerUserResto(mapResto);			
		}
		System.out.println("맛집 저장 완료");
//		session.getMapper(MapRestoRepository.class).registerRestos(resto);
		// 맛지도에 등록할 맛집 등록
		session.getMapper(MapRestoRepository.class).registerTags(mapResto);
	}

	@Override
	public List<MapRestoLikeDto> getMapRestosList(MapRestoListParamDto param) throws Exception {
		int total = param.getTotal();
		int num = param.getNum();
//		if (total < num * 12) {
//			param.setTotal(total);
//		} else {
//			param.setTotal((num + 1)* 12);
//		}
		param.setTotal((num + 1)* 12);
		System.out.println("service param: " + param);
		List<MapRestoLikeDto> mapRestosList = session.getMapper(MapRestoRepository.class).getMapRestosLikeList(param);
		for (MapRestoLikeDto mapRestoLikeDto : mapRestosList) {
			mapRestoLikeDto.setFileInfo(session.getMapper(MapRestoRepository.class).getFileInfo(mapRestoLikeDto.getMapRestoNo()));
		}
		return mapRestosList;
	}

	@Override
	public void registerFileTest(FileInfoDto file) throws Exception {
		session.getMapper(MapRestoRepository.class).registerFileTest(file);
	}

	@Override
	public int getTotalMapResto() throws Exception {
		return session.getMapper(MapRestoRepository.class).getTotalMapResto();
	}

	@Override
	public List<MapRestoMypageDto> getMyMapResto(Map<String, Object> map) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userId", map.get("userId"));
		param.put("start", (int)map.get("num") * 4);
		param.put("listsize", 4);
		List<MapRestoMypageDto> myMapRestoList = session.getMapper(MapRestoRepository.class).getMyMapResto(param);
		for (MapRestoMypageDto mapResto : myMapRestoList) {
			mapResto.setFileInfo(session.getMapper(MapRestoRepository.class).getFileInfo(mapResto.getMapRestoNo()));
		}
		return myMapRestoList;
	}

	@Override
	public List<MapRestoMypageDto> getLikeMapResto(Map<String, Object> map) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userId", map.get("userId"));
		param.put("start", (int)map.get("num") * 4);
		param.put("listsize", 4);
		List<MapRestoMypageDto> likeMapRestoList = session.getMapper(MapRestoRepository.class).getLikeMapResto(param);
		for (MapRestoMypageDto mapResto : likeMapRestoList) {
			mapResto.setFileInfo(session.getMapper(MapRestoRepository.class).getFileInfo(mapResto.getMapRestoNo()));
		}
		return likeMapRestoList;
	}

	@Override
	public MapRestoDto getDetailMapResto(String mapRestoNo) throws Exception {
		MapRestoDto detailMapResto = session.getMapper(MapRestoRepository.class).getDetailMapResto(mapRestoNo);
//		detailMapResto.setFileInfo(session.getMapper(MapRestoRepository.class).getFileInfo(mapRestoNo));
		return detailMapResto;
	}

	@Override
	public int getTotalMyMapResto(String userId) throws Exception {
		return session.getMapper(MapRestoRepository.class).getTotalMyMapResto(userId);
	}

	@Override
	public int getTotalLikeMapResto(String userId) throws Exception {
		return session.getMapper(MapRestoRepository.class).getTotalLikeMapResto(userId);
	}

	@Override
	public List<RestoDto> getUserRestoList(String mapRestoNo) throws Exception {
		return session.getMapper(MapRestoRepository.class).getUserRestoList(mapRestoNo);
	}

	@Override
	public void changeLike(LikeInfoDto likeInfo) throws Exception {
		session.getMapper(MapRestoRepository.class).changeLike(likeInfo);
	}

}
