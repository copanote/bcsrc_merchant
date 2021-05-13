package com.merchant.demo.xml.lpb.api.comm;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class RequestHeader {
	@JsonProperty("Command") 
	@LpbField(signature = true) 
	private String command;
	
	@JsonProperty("Password") 
	@LpbField(signature = true) 
	private String password;
	
	@JsonProperty("SystemTraceId") 
	@LpbField(signature = true)
	private String systemTraceId;
	
	@JsonProperty("RequestDateTime") 
	@LpbField(signature = true)
	private String requestDateTime;
	
	@JsonProperty("ChannelType")
	@LpbField(signature = true)
	private String channelType;
	
}
