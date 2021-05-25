package com.merchant.demo.xml.lpb.api.comm.header;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.merchant.demo.xml.lpb.api.comm.LpbMsg;
import com.merchant.demo.xml.lpb.api.comm.LpbMsg.LpbField;

import lombok.Data;

@Data
public class ResponseHeader implements LpbMsg {
	@JsonProperty("Command")
	@LpbField(signature = true) 
	private String command;
	
	@JsonProperty("SystemTraceId")
	@LpbField(signature = true) 
	private String systemTraceId;
	
	@JsonProperty("ResponseDateTime")
	@LpbField(signature = true) 
	private String responseDateTime;
	
	@JsonProperty("TransId")
	@LpbField(signature = true) 
	private String transId;
	
	@JsonProperty("ResponseCode")
	@LpbField(signature = true) 
	private String responseCode;
	
	@JsonProperty("ResponseMessage")
	@LpbField(signature = true) 
	private String responseMessage;

}
