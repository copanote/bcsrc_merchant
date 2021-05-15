package com.merchant.demo.xml.lpb.api.otpRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.merchant.demo.xml.cipher.Ciphers;
import com.merchant.demo.xml.lpb.api.comm.LpbMsg;
import com.merchant.demo.xml.lpb.api.comm.header.RequestHeader;
import com.merchant.demo.xml.lpb.api.otpRequest.vo.OtpRequestVo;
import com.merchant.demo.xml.lpb.soap.req.RequestEnvelope;

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
		String data = LpbMsg.makeSignedData(requestHeader, otpRequestVo);
		return Ciphers.sha256WithRsaSign(data, Ciphers.bcPrivateKey);
	}
	
	public String toXml() throws JsonProcessingException {
		XmlMapper xmlMapper = new XmlMapper();
		return xmlMapper.writeValueAsString(this);
	}
	
	public RequestEnvelope sealEnvelope() throws Exception {
		return RequestEnvelope.of("Bccard", toXml());
	}
}
