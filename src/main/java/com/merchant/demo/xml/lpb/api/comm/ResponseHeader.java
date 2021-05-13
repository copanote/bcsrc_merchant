package com.merchant.demo.xml.lpb.api.comm;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ResponseHeader {
	@JsonProperty("Command")
	@LpbField(signature = true) 
	private String command;
	
	@JsonProperty("SystemTraceId")
	@LpbField(signature = true) 
	private String systemTraceId;
	
	@JsonProperty("ResponseDateTime")
	@LpbField(signature = true) 
	private String responseDateTime;
	
	@JsonProperty("TrnsId")
	@LpbField(signature = true) 
	private String trnsId;
	
	@JsonProperty("ResponseCode")
	@LpbField(signature = true) 
	private String responseCode;
	
	@JsonProperty("ResponseMessage")
	@LpbField(signature = true) 
	private String responseMessage;

}
