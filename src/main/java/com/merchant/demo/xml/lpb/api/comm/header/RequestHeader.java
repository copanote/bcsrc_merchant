package com.merchant.demo.xml.lpb.api.comm.header;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.annotation.Resource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.merchant.demo.xml.lpb.api.comm.LpbMsg;
import com.merchant.demo.xml.lpb.api.comm.LpbMsg.LpbField;

import lombok.Data;

@Data
public class RequestHeader implements LpbMsg {
	
	@JsonIgnore
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
	
	
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
	private final String channelType = "API";
	
	public RequestHeader(String command, String password, String systemTraceId, String requestDateTime) {
		super();
		this.command = command;
		this.password = password;
		this.systemTraceId = systemTraceId;
		this.requestDateTime = requestDateTime;
	}
	
	public static RequestHeader of(String command, String password, String systemTraceId, LocalDateTime requestDataTime) {
		String formattedDate = requestDataTime.format(formatter);
		return new RequestHeader(command, password, systemTraceId, formattedDate);
	}
	
	
	
}
