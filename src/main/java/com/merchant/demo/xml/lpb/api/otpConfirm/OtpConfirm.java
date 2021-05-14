package com.merchant.demo.xml.lpb.api.otpConfirm;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.merchant.demo.xml.cipher.Ciphers;
import com.merchant.demo.xml.lpb.api.comm.LpbMsg;
import com.merchant.demo.xml.lpb.api.comm.RequestHeader;
import com.merchant.demo.xml.lpb.api.otpConfirm.vo.OtpConfirmVo;
import com.merchant.demo.xml.lpb.soap.req.RequestEnvelope;

import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "BccardOnlineRequest")
public class OtpConfirm  {
	@JsonProperty("RequestHeader")
	private RequestHeader requestHeader;
	@JsonProperty("BccardOnlineRequestBody")
	private OtpConfirmVo otpConfirm;
	@JsonProperty("Signature")
	private String signature;
	
	public String createSignature() throws Exception {
		String data = LpbMsg.makeFullSignatureData(requestHeader, otpConfirm);
		return Ciphers.sha256WithRsaSign(data, Ciphers.bcPrivateKey);
	}
	
	public String toXml() throws JsonProcessingException {
		XmlMapper xmlMapper = new XmlMapper();
		return xmlMapper.writeValueAsString(this);
	}
	
	public RequestEnvelope envelope() throws Exception {
		return RequestEnvelope.of("Bccard", toXml());
	}

}
