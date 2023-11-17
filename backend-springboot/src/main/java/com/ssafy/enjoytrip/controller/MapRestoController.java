package com.ssafy.enjoytrip.controller;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.enjoytrip.dto.FileInfoDto;
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
	
	@Value("${file.path}")
	private String uploadPath;
	
	@Value("${file.path.upload-images}")
	private String uploadImagePath;
	
	@Value("${file.path.upload-files}")
	private String uploadFilePath;
	
	@Autowired
	public MapRestoController(MapRestoService mapRestoService) {
		super();
		this.mapRestoService = mapRestoService;
	}
	
	@PostMapping
	public ResponseEntity<?> makeMapRestaurant(@RequestParam("fileInfo") MultipartFile fileInfo, @RequestBody MapRestoDto mapResto) {
		log.debug("MapRestoController makeMapRestaurant mapResto: ", mapResto);
		
		if (mapResto.getFileInfo() == null) {
			String today = new SimpleDateFormat("yyMMdd").format(new Date());
			String saveFolder = uploadPath + File.separator + today;
			logger.debug("저장 폴더 : {}", saveFolder);
			File folder = new File(saveFolder);
			if (!folder.exists())
				folder.mkdirs();
			FileInfoDto fileInfoDto = new FileInfoDto();
//			for (MultipartFile mfile : files) {
//				FileInfoDto fileInfoDto = new FileInfoDto();
				String originalFileName = fileInfo.getOriginalFilename();
				if (!originalFileName.isEmpty()) {
					String saveFileName = UUID.randomUUID().toString()
							+ originalFileName.substring(originalFileName.lastIndexOf('.'));
					fileInfoDto.setSaveFolder(today);
					fileInfoDto.setOriginalFile(originalFileName);
					fileInfoDto.setSaveFile(saveFileName);
//					logger.debug("원본 파일 이름 : {}, 실제 저장 파일 이름 : {}", fileInfo.getOriginalFilename(), saveFileName);
					try {
						fileInfo.transferTo(new File(folder, saveFileName));
					} catch (IllegalStateException e) {
						System.out.println("파일 업록드 에러");
						e.printStackTrace();
					} catch (IOException e) {
						System.out.println("파일 업록드 에러");
						e.printStackTrace();
					}
				}
//			}
			mapResto.setFileInfo(fileInfoDto);
		}
		
		try {
			mapRestoService.makeMapResto(mapResto);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping
	public ResponseEntity<?> listMapRestaurant(@RequestParam("num") int num) {
		log.debug("MapRestoController listMapRestaurant map: ", num);
		try {
			List<MapRestoDto> mapRestosList = mapRestoService.getMapRestosList(num);
			HttpHeaders header = new HttpHeaders();
			header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
			return ResponseEntity.ok().headers(header).body(mapRestosList);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping("/fileupload")
	public ResponseEntity<?> fileUpload(@RequestParam("fileInfo") MultipartFile fileInfo) {
		Map<String,Object> map = new HashMap();
		String today = new SimpleDateFormat("yyMMdd").format(new Date());
		String saveFolder = uploadPath + File.separator + today;
		logger.debug("저장 폴더 : {}", saveFolder);
		File folder = new File(saveFolder);
		if (!folder.exists())
			folder.mkdirs();
		FileInfoDto fileInfoDto = new FileInfoDto();
//		for (MultipartFile mfile : files) {
//			FileInfoDto fileInfoDto = new FileInfoDto();
			String originalFileName = fileInfo.getOriginalFilename();
			if (!originalFileName.isEmpty()) {
				String saveFileName = UUID.randomUUID().toString()
						+ originalFileName.substring(originalFileName.lastIndexOf('.'));
				fileInfoDto.setSaveFolder(today);
				fileInfoDto.setOriginalFile(originalFileName);
				fileInfoDto.setSaveFile(saveFileName);
//				logger.debug("원본 파일 이름 : {}, 실제 저장 파일 이름 : {}", fileInfo.getOriginalFilename(), saveFileName);
				try {
					fileInfo.transferTo(new File(folder, saveFileName));
					map.put("resmsg", "입력성공");
				} catch (IllegalStateException e) {
					System.out.println("파일 업록드 에러");
					e.printStackTrace();
				} catch (IOException e) {
					System.out.println("파일 업록드 에러");
					e.printStackTrace();
				}
			}
//		}
		return ResponseEntity.ok().body(map);
	}
	
}
