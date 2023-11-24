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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.enjoytrip.dto.FileInfoDto;
import com.ssafy.enjoytrip.dto.LikeInfoDto;
import com.ssafy.enjoytrip.dto.MapRestoDto;
import com.ssafy.enjoytrip.dto.MapRestoLikeDto;
import com.ssafy.enjoytrip.dto.MapRestoListParamDto;
import com.ssafy.enjoytrip.dto.MapRestoMypageDto;
import com.ssafy.enjoytrip.dto.RestoDto;
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
	
//	@RequestParam("fileInfo") MultipartFile fileInfo, 
	@PostMapping("reg")
	public ResponseEntity<?> makeMapRestaurant(
			@ModelAttribute MapRestoDto mapResto) {
		log.info("MapRestoController makeMapRestaurant mapResto: {}", mapResto);

		MultipartFile fileInfo = mapResto.getFile();
		
		if (fileInfo != null) {
			String today = new SimpleDateFormat("yyMMdd").format(new Date());
			String saveFolder = uploadPath + File.separator + today;
			logger.debug("저장 폴더 : {}", saveFolder);
			File folder = new File(saveFolder);
			if (!folder.exists())
				folder.mkdirs();
			FileInfoDto fileInfoDto = new FileInfoDto();
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
						System.out.println("파일 업로드 에러");
						e.printStackTrace();
					} catch (IOException e) {
						System.out.println("파일 업로드 에러");
						e.printStackTrace();
					}
				}
			mapResto.setFileInfo(fileInfoDto);
		}
		try {
			mapRestoService.makeMapResto(mapResto);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
//	public ResponseEntity<?> listMapRestaurant(@RequestParam("num") int num, @RequestParam("total") int totalMap) {
	@PostMapping("/list")
	public ResponseEntity<?> listMapRestaurant(@RequestBody MapRestoListParamDto param) {
		log.info("MapRestoController listMapRestaurant param: {}", param.toString());
		try {
			Map<String,Object> map = new HashMap();
			List<MapRestoLikeDto> mapRestosList = mapRestoService.getMapRestosList(param);
			int total = mapRestoService.getTotalMapResto(param.getCheckUserId());
			map.put("list", mapRestosList);
			map.put("total", total);
			HttpHeaders header = new HttpHeaders();
			header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
			return ResponseEntity.ok().headers(header).body(map);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping("/fileupload")
	public ResponseEntity<?> fileUpload(@RequestParam("fileInfo") MultipartFile fileInfo) throws Exception {
		Map<String,Object> map = new HashMap();
		String today = new SimpleDateFormat("yyMMdd").format(new Date());
		String saveFolder = uploadPath + File.separator + today;
		logger.debug("저장 폴더 : {}", saveFolder);
		File folder = new File(saveFolder);
		if (!folder.exists())
			folder.mkdirs();
		FileInfoDto fileInfoDto = new FileInfoDto();

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
			mapRestoService.registerFileTest(fileInfoDto);
		return ResponseEntity.ok().body(map);
	}
	
	//내가 작성한 맛지도 가져오기
	@GetMapping("/myMapResto")
	public ResponseEntity<?> listMyMapRestaurant(@RequestParam("num") int num, @RequestParam String userId) {
		try {
			Map<String,Object> param = new HashMap(); // 인자값 담을 map
			param.put("userId", userId);
			param.put("num", num);
			Map<String,Object> result = new HashMap(); // 결과 담을 map
			List<MapRestoMypageDto> myMapResto = mapRestoService.getMyMapResto(param);
			int totalMyMapResto = mapRestoService.getTotalMyMapResto(userId);
			result.put("myMapList", myMapResto);
			result.put("totalMyMapResto", totalMyMapResto);
			HttpHeaders header = new HttpHeaders();
			header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
			return ResponseEntity.ok().headers(header).body(result);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	//좋아요 누른 맛지도 가져오기
	@GetMapping("/likeMapResto")
	public ResponseEntity<?> listLikeMapRestaurant(@RequestParam("num") int num, @RequestParam String userId) {
		try {
			
			Map<String,Object> param = new HashMap(); // 인자값 담을 map
			param.put("userId", userId);
			param.put("num", num);
			System.out.println("listLikeMapRestaurant param: " + param);
			List<MapRestoMypageDto> likeMapResto = mapRestoService.getLikeMapResto(param);
			int totalLikeMapResto = mapRestoService.getTotalLikeMapResto(userId);
			
			Map<String,Object> result = new HashMap<String, Object>(); // 결과 담을 map
			result.put("likeMapList", likeMapResto);
			result.put("totalLikeMapResto", totalLikeMapResto);
			
			HttpHeaders header = new HttpHeaders();
			header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
			return ResponseEntity.ok().headers(header).body(result);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	// 맛지도 상세 보기
	@GetMapping("/view/{mapRestoNo}")
	public ResponseEntity<?> getDetailMapResto(@PathVariable("mapRestoNo") String mapRestoNo) {
		try {
			Map<String,Object> map = new HashMap();
			MapRestoDto detailMapResto = mapRestoService.getDetailMapResto(mapRestoNo);
			List<RestoDto> userRestoList = mapRestoService.getUserRestoList(mapRestoNo);
			map.put("mapResto", detailMapResto);
			map.put("userRestoList", userRestoList);
			HttpHeaders header = new HttpHeaders();
			header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
			return ResponseEntity.ok().headers(header).body(map);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	// 좋아요 값 변경
	@PostMapping("/like")
	public ResponseEntity<?> changeLike(@RequestBody LikeInfoDto likeInfo) {
		try {
			Map<String,Object> map = new HashMap();
			mapRestoService.changeLike(likeInfo);
			map.put("resMsg", "좋아요 변경 성공");
			return ResponseEntity.ok().body(map);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	
	// 특정 맛지도에 저장된 식당들 가져오기
	@GetMapping("/userResto/{mapRestoNo}")
	public ResponseEntity<?> getUserMapResto(@PathVariable("mapRestoNo") String mapRestoNo) {
		try {
			Map<String,Object> map = new HashMap();
			MapRestoDto detailMapResto = mapRestoService.getDetailMapResto(mapRestoNo);
			List<RestoDto> userRestoList = mapRestoService.getUserRestoList(mapRestoNo);
			map.put("mapResto", detailMapResto);
			map.put("userRestoList", userRestoList);
			HttpHeaders header = new HttpHeaders();
			header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
			return ResponseEntity.ok().headers(header).body(map);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	// 맛집 목록 가져오기
	@GetMapping("/listResto")
	public ResponseEntity<?> getRestoList(@RequestParam("tag") String tag) {
		System.out.println("맛집 목록 가져와 tag" + tag);
		try {
			Map<String,Object> map = new HashMap();
			List<RestoDto> resto = mapRestoService.getResto(tag);
			map.put("list", resto);
			HttpHeaders header = new HttpHeaders();
			header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
			return ResponseEntity.ok().headers(header).body(map);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
}
