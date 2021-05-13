package com.merchant.demo.xml.lpb.api.otpRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.merchant.demo.xml.cipher.Ciphers;
import com.merchant.demo.xml.lpb.api.comm.LpbMsg;
import com.merchant.demo.xml.lpb.api.comm.RequestHeader;
import com.merchant.demo.xml.lpb.api.otpRequest.vo.OtpRequestVo;

import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "BccardOnlineRequest")
public class OtpRequest {
	@JsonProperty("RequestHeader")
	private RequestHeader requestHeader;
	@JsonProperty("BccardOnlineRequestBody")
	private OtpRequestVo otpRequestVo;
	@JsonProperty("Signature")
	private String signature;
	
	public String createSignature() throws Exception {
		String data = LpbMsg.makeFullSignatureData(requestHeader, otpRequestVo);
		System.out.println(data);
		return Ciphers.sha256WithRsaSign(data, Ciphers.bcPrivateKey);
	}
	
}
