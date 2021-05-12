package com.merchant.demo.xml.lpb.api.comm;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class RequestHeader {
	@JsonProperty("Command")
	private String command;
	@JsonProperty("Password")
	private String password;
	@JsonProperty("SystemTraceId")
	private String systemTraceId;
	@JsonProperty("RequestDateTime")
	private String requestDateTime;
	@JsonProperty("ChannelType")
	private String channelType;
	
}
