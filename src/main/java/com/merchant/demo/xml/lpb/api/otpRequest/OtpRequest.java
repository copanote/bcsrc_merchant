package com.merchant.demo.xml.lpb.api.otpRequest;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import lombok.Getter;

@Data
@JacksonXmlRootElement(localName = "BccardOnlineRequest")
public class OtpRequest {
	
	@JsonIgnore
	public static final String COMMAND = "bcOnlineRequest";
	
	
	@JsonProperty("RequestHeader")
	private RequestHeader requestHeader;
	@JsonProperty("BccardOnlineRequestBody")
	private OtpRequestVo otpRequestVo;
	@JsonProperty("Signature")
	private String signature;
	
	public void createAndSetSignature() throws Exception {
		String data = LpbMsg.makeSignedData(requestHeader, otpRequestVo);
		this.setSignature(Ciphers.sha256WithRsaSign(data, Ciphers.bcPrivateKey));
	}
	
	public String toXml() throws JsonProcessingException {
		XmlMapper xmlMapper = new XmlMapper();
		return xmlMapper.writeValueAsString(this);
	}
	
	public RequestEnvelope sealEnvelope(String user) throws Exception {
		return RequestEnvelope.of(user, toXml());
	}
}
