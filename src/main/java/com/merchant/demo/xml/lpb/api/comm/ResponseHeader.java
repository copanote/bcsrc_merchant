package com.merchant.demo.xml.lpb.api.comm;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ResponseHeader {
	@JsonProperty("Command")
	private String command;
	@JsonProperty("SystemTraceId")
	private String systemTraceId;
	@JsonProperty("ResponseDateTime")
	private String responseDateTime;
	@JsonProperty("TrnsId")
	private String trnsId;
	@JsonProperty("ResponseCode")
	private String responseCode;
	@JsonProperty("ResponseMessage")
	private String responseMessage;

}
