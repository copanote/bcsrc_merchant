package com.merchant.demo.xml.lpb.soap.req;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.Data;

@Data
public class RequestBody {
	
	
	
	public RequestBody(Execute execute) {
		this.execute = execute;
	}


	@JacksonXmlProperty(namespace = "http://function.ws.core.gateway.com/", localName = "execute")
	private Execute execute;
	
	
	public static RequestBody of(String user, String data) throws Exception {
		Execute exe = Execute.of(user, data);
		return new RequestBody(exe);
	}
	
}
