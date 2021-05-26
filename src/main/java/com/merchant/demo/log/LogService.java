package com.merchant.demo.log;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.merchant.demo.xml.lpb.api.comm.header.RequestHeader;

@Service
public class LogService {
	
	
	@SrcLoggable(owner = "SRC", name = "service", direction = InOutDivision.OUTBOUND)
	public RequestHeader service(int a, int b, String c) {
		System.out.println("service");
		
		return RequestHeader.of("CCC", "PPP", c, LocalDateTime.now());
	}
	
	
	@SrcLoggable(owner = "SRC", name = "hello", direction = InOutDivision.OUTBOUND)
	public String helloWorld(RequestHeader header) {
		System.out.println("helloWorld");
		return "result";
	}

}
