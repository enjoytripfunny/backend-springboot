package com.ssafy.enjoytrip.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.dto.MapRestoDto;
import com.ssafy.enjoytrip.service.MapRestoService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin("*")
@RequestMapping("/mapresto")
@Slf4j
public class MapRestoController {
	private static final Logger logger = LoggerFactory.getLogger(TripBoardController.class);
	private MapRestoService mapRestoService;
	
	@Autowired
	public MapRestoController(MapRestoService mapRestoService) {
		super();
		this.mapRestoService = mapRestoService;
	}
	
	@PostMapping
	public ResponseEntity<?> makeMapRestaurant(@RequestBody MapRestoDto mapResto) {
		log.debug("MapRestoController makeMapRestaurant mapResto: ", mapResto);
		try {
			mapRestoService.makeMapResto(mapResto);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
