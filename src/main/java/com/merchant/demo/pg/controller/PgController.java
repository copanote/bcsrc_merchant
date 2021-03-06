package com.merchant.demo.pg.controller;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.merchant.demo.pg.controller.vo.SrcReturnVo;
import com.merchant.demo.pg.service.SrcService;
import com.merchant.demo.pg.service.api.checkout.Checkout;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class PgController {
	
	@Autowired SrcService srcService;
	
	//http://localhost:7070/src/pg/rd?resultCode=0000&resultMessage=Success&srcCorrelationId=1234&srciTransactionId=5678
	@RequestMapping(value = "pg/rd")
	@ResponseBody
	public String credential(SrcReturnVo inVo) {
		log.debug(inVo.toString());
		
		Checkout c = new Checkout();
		c.setSrcCorrelationId(inVo.getSrcCorrelationId());
		c.setSrciTransactionId(inVo.getSrciTransactionId());
		
		String res = srcService.checkoutMock(c);
		
		return "Result: " + res;
	}
	
	
	@GetMapping(value = "pg/mall", produces =  MediaType.TEXT_HTML_VALUE)
	@ResponseBody
	public String pgMall() {
		Document doc = null;//	    	ClassPathResource resource = new ClassPathResource("static/vn/index.html");

    	ClassPathResource resource = new ClassPathResource("static/pgmall/mer.html");
		try {
    	    Path path = Paths.get(resource.getURI());
    	    byte[] b = Files.readAllBytes(path);
			doc = Jsoup.parse(new String(b, Charset.forName("utf-8")));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return doc.toString();
	}
	
	
	@GetMapping(value = "pg/test")
	public String pgLocal() {
		return "redirect:http://localhost:3000";
	}
	
	@GetMapping(value = "pg/test2")
	@ResponseBody
	public SrcReturnVo pgLocal2() {
		
		SrcReturnVo vo = new SrcReturnVo();
		vo.setResultCode("0000");
		vo.setResultMessage("Suc");
		vo.setSrcCorrelationId(UUID.randomUUID().toString());
		vo.setSrciTransactionId(UUID.randomUUID().toString());
		
		return vo;
	}
	
	@GetMapping(value = "pg/test3")
	@ResponseBody
	public SrcReturnVo pgLocal3(SrcReturnVo inVo) {
		return inVo;
	}
	
}
