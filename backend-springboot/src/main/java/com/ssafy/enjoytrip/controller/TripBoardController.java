package com.ssafy.enjoytrip.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.dto.TripBoardDto;
import com.ssafy.enjoytrip.service.TripBoardServcie;
import com.ssafy.util.SizeConstant;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin("*")
@RestController
@RequestMapping("/tripboard")
@Slf4j
public class TripBoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(TripBoardController.class);
	private TripBoardServcie tripBoardService;

	@Autowired
	public TripBoardController(TripBoardServcie tripBoardService) {
		super();
		this.tripBoardService = tripBoardService;
	}
	
	@PostMapping("/write")
//	public ResponseEntity<?> writeArticle(@RequestBody String board) {
//		logger.info("writeArticle TripBoardDto - {}", board.);
//		return new ResponseEntity<Void>(HttpStatus.CREATED);
//	}
	public ResponseEntity<?> writeArticle(@RequestBody TripBoardDto board) {
		logger.debug("writeArticle TripBoardDto - {}", board);
		Map<String,Object> map = new HashMap();
		try {
			tripBoardService.writeArticle(board);
			map.put("resmsg", "입력성공");
			return new ResponseEntity<>(map, HttpStatus.CREATED);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/list")
	public ResponseEntity<?> listArticle(@RequestParam Map<String, String> map) {
		logger.debug("list param: ", map);
		try {
			List<TripBoardDto> list = tripBoardService.listArticle(map);
			int totalPage = (tripBoardService.getTotalArticleCount(map) - 1) / SizeConstant.LIST_SIZE + 1;
			logger.debug("list totalPage: ", totalPage);
			Map<String,Object> res = new HashMap();
			if (list != null & !list.isEmpty()) {
				res.put("list", list);
				res.put("totalPage", totalPage);
				return new ResponseEntity<>(res, HttpStatus.OK);
//				return new ResponseEntity<List<TripBoardDto>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
		
		// 파라미터 값 넣기
	}
	
	@GetMapping("/view/{articleno}")
	public ResponseEntity<?> getArticle(@PathVariable("articleno") int articleNo, @RequestParam Map<String, String> map) {
		logger.debug("getArticle articleNo - {}", articleNo);
		try {
			TripBoardDto boardDto = tripBoardService.getArticle(articleNo);
			tripBoardService.updateHit(articleNo);
			if (boardDto == null) {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<TripBoardDto>(boardDto, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
		
		// 파라미터 값 넣기
		
	}
	
	@PutMapping("/modify")
	public ResponseEntity<?> modifyArticle(@RequestBody TripBoardDto board) {
		logger.debug("modifyArticle board - {}", board);
		Map<String,Object> map = new HashMap();
		try {
			tripBoardService.modifyArticle(board);
			map.put("resmsg", "수정 성공");
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
		
		// 파라미터 값 넣기
	}
	
	@DeleteMapping("/delete/{articleno}")
	public ResponseEntity<?> deleteArticle(@PathVariable("articleno") int articleNo) {
		Map<String,Object> map = new HashMap();
		try {
			tripBoardService.deleteArticle(articleNo);
			map.put("resmsg", "삭제 성공");
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
