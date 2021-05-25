package com.merchant.demo;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.merchant.demo.log.LogService;
import com.merchant.demo.xml.lpb.api.comm.header.RequestHeader;

@SpringBootTest
class DemoApplicationTests {
	
	@Autowired LogService service;

	@Test
	void contextLoads() {
		service.service(1,2, "c");
	}
	
	
	@Test
	void contextLoadss() {
		
		service.helloWorld(RequestHeader.of("ccc","paww", UUID.randomUUID().toString(), LocalDateTime.now()));
	}

}
