package com.merchant.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.merchant.demo.log.LogService;

@SpringBootTest
class DemoApplicationTests {
	
	@Autowired LogService service;

	@Test
	void contextLoads() {
		service.service();
	}
	
	
	@Test
	void contextLoadss() {
		service.helloWorld();
	}

}
