package com.ssafy.enjoytrip.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.enjoytrip.dto.MapRestoDto;
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
		session.getMapper(MapRestoRepository.class).makeMapResto(mapResto);
		// 파일 등록
		session.getMapper(MapRestoRepository.class).registerFile(mapResto);
		// 맛집 등록
		session.getMapper(MapRestoRepository.class).registerRestos(mapResto);
		// 맛지도에 등록할 맛집 등록
		session.getMapper(MapRestoRepository.class).registerUserResto(mapResto);
//		List<String> existResto = new ArrayList<>();
//		List<RestoDto> newResto = new ArrayList<RestoDto>();
//		for (RestoDto restoDto : mapResto.getRestos()) {
//			String res = null;
//			res = session.getMapper(MapRestoRepository.class).getResto(restoDto.getRestoApiId());
//			log.debug("MapRestoService makeMapResto/getRestos res: ", res);
//			if (res != null) {
//				session.getMapper(MapRestoRepository.class).registerRestos(mapResto);
//			}
//		}
	}

}
