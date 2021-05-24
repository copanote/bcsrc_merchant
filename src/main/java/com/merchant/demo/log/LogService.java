package com.merchant.demo.log;

import org.springframework.stereotype.Service;

@Service
public class LogService {
	
	
	@Loggable
	public void service() {
		System.out.println("service");
	}
	
	
	@Loggable
	public void helloWorld() {
		System.out.println("helloWorld");
	}

}
