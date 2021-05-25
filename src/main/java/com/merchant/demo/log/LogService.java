package com.merchant.demo.log;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.merchant.demo.xml.lpb.api.comm.header.RequestHeader;

@Service
public class LogService {
	
	
	@Loggable
	public RequestHeader service(int a, int b, String c) {
		System.out.println("service");
		
		return RequestHeader.of("CCC", "PPP", c, LocalDateTime.now());
	}
	
	
	@Loggable
	public String helloWorld(RequestHeader header) {
		System.out.println("helloWorld");
		
		return "result";
	}

}
