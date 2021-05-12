package com.merchant.demo.xml.lpb.api.otpRequest.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class OtpRequestResponse {
	@JsonProperty("RsltCode")
	private String rsltCode;
	@JsonProperty("RsltMsg")
	private String rsltMsg;
	@JsonProperty("SessionId")
	private String sessionId;

}
