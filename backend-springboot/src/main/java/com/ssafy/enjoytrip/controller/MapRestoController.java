package com.ssafy.enjoytrip.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	
}
