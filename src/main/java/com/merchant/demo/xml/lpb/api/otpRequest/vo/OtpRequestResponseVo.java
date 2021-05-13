package com.merchant.demo.xml.lpb.api.otpRequest.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.merchant.demo.xml.lpb.api.comm.LpbMsg;

import lombok.Data;

@Data
public class OtpRequestResponseVo implements LpbMsg {
	@JsonProperty("RsltCode")
	@LpbField(signature = true) 
	private String rsltCode;
	
	@JsonProperty("RsltMsg")
	@LpbField(signature = false) 
	private String rsltMsg;
	
	@JsonProperty("SessionId")
	@LpbField(signature = true) 
	private String sessionId;

}
