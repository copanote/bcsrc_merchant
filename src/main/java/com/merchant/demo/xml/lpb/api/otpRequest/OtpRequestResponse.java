package com.merchant.demo.xml.lpb.api.otpRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.merchant.demo.xml.cipher.Ciphers;
import com.merchant.demo.xml.lpb.api.comm.XmlUtils;
import com.merchant.demo.xml.lpb.api.comm.LpbMsg;
import com.merchant.demo.xml.lpb.api.comm.header.ResponseHeader;
import com.merchant.demo.xml.lpb.api.otpRequest.vo.OtpRequestResponseVo;
import com.merchant.demo.xml.lpb.soap.res.ResponseEnvelope;

import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "CommonResponse")
public class OtpRequestResponse {
	
	@JsonProperty("ResponseHeader")
	private ResponseHeader responseHeader;
	@JsonProperty("BccardOnlineRequestBody")
	private OtpRequestResponseVo bccardOnlineRequestBody;
	@JsonProperty("Signature")
	private String signature;
	
	public boolean veryfySignature() throws Exception {
		String sigData = LpbMsg.makeSignedData(responseHeader, bccardOnlineRequestBody);
		return Ciphers.verifyRsaSignature(sigData, signature, Ciphers.lpbDevPublicKey);
	}
	
	public static OtpRequestResponse openEnvelope(ResponseEnvelope r) throws Exception {
		String data = r.getBody().getExecuteResponse().decryptedResponseData();
		return XmlUtils.unmarshalXml(data, OtpRequestResponse.class);
	}
	
}
