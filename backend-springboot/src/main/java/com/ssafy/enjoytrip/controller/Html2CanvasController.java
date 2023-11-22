package com.ssafy.enjoytrip.controller;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping(value="/html2canvas")
public class Html2CanvasController {
	@GetMapping("/proxy.json")
	@ResponseBody
	public byte[] html2canvasProxy(HttpServletRequest req) {
		System.out.println("프록시 서버 들어오니");
		byte[] data = null;
		try {
			URL url = new URL(URLDecoder.decode(req.getParameter("url"), java.nio.charset.StandardCharsets.UTF_8.toString()));
			System.out.println("url : " + url);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			
			if(connection.getResponseCode() == 200) {
				System.out.println("200코드 반환");
				data = IOUtils.toByteArray(connection.getInputStream());
			} else {
				System.out.println("responseCode : " + connection.getResponseCode());
			}
		} catch (MalformedURLException e) {
			data = "wrong URL".getBytes(java.nio.charset.StandardCharsets.UTF_8);
		} catch(Exception e) {
			System.out.println(e);
		}
		return data;
	}
	
//	@ResponseBody
//	@RequestMapping(value = { "ImgSaveTest" }, method = RequestMethod.POST)
//	public ModelMap ImgSaveTest(@RequestParam HashMap<Object, Object> param, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
//		ModelMap map = new ModelMap();
//		
//		String binaryData = request.getParameter("imgSrc");
//		FileOutputStream stream = null;
//		try{
//			System.out.println("binary file   "  + binaryData);
//			if(binaryData == null || binaryData.trim().equals("")) {
//			    throw new Exception();
//			}
//			binaryData = binaryData.replaceAll("data:image/png;base64,", "");
//			byte[] file = Base64.decodeBase64(binaryData);
//			String fileName=  UUID.randomUUID().toString();
//			
//			stream = new FileOutputStream("E:/test2/"+fileName+".png");
//			stream.write(file);
//			stream.close();
//			System.out.println("캡처 저장");
//		    
//		}catch(Exception e){
//			e.printStackTrace();
//			System.out.println("에러 발생");
//		}finally{
//			if(stream != null) {
//				stream.close();
//			}
//		}
//		
//		map.addAttribute("resultMap", "");
//		return map;
//	}
	
}
