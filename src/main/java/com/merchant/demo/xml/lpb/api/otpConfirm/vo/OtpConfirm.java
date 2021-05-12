package com.merchant.demo.xml.lpb.api.otpConfirm.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class OtpConfirm {
	@JsonProperty("mobileNo")
	private String mobileNo;
	@JsonProperty("OtpNo")
	private String otpNo;
	@JsonProperty("SessionId")
	private String sessionId;
	@JsonProperty("RefId")
	private String refId;
}
