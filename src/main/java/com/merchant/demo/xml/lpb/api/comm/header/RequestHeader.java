package com.merchant.demo.xml.lpb.api.comm.header;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.merchant.demo.xml.lpb.api.comm.LpbMsg;
import com.merchant.demo.xml.lpb.api.comm.LpbMsg.LpbField;

import lombok.Data;

@Data
public class RequestHeader implements LpbMsg {
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
