package com.merchant.demo.xml.lpb.api.otpRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.merchant.demo.xml.lpb.api.comm.ResponseHeader;
import com.merchant.demo.xml.lpb.api.otpRequest.vo.OtpRequestResponse;

import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "CommonResponse")
public class OnlineRequestResponse {
	
	@JsonProperty("ResponseHeader")
	private ResponseHeader responseHeader;
	@JsonProperty("BccardOnlineRequestBody")
	private OtpRequestResponse bccardOnlineRequestBody;
	@JsonProperty("Signature")
	private String signature;

}
