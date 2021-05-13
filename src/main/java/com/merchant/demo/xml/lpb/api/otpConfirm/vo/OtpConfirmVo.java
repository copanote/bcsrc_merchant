package com.merchant.demo.xml.lpb.api.otpConfirm.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.merchant.demo.xml.lpb.api.comm.LpbMsg;

import lombok.Data;

@Data
public class OtpConfirmVo implements LpbMsg {
	@JsonProperty("mobileNo")
	@LpbField(signature = true) 
	private String mobileNo;
	
	@JsonProperty("OtpNo")
	@LpbField(signature = true) 
	private String otpNo;
	
	@JsonProperty("SessionId")
	@LpbField(signature = true) 
	private String sessionId;
	
	@JsonProperty("RefId")
	@LpbField(signature = true) 
	private String refId;
}
