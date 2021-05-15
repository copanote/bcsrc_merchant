package com.merchant.demo.xml.lpb.api.otpConfirm;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.merchant.demo.xml.cipher.Ciphers;
import com.merchant.demo.xml.lpb.api.comm.XmlUtils;
import com.merchant.demo.xml.lpb.api.comm.LpbMsg;
import com.merchant.demo.xml.lpb.api.comm.header.ResponseHeader;
import com.merchant.demo.xml.lpb.api.otpConfirm.vo.OtpConfirmResponseVo;
import com.merchant.demo.xml.lpb.api.otpRequest.OtpRequestResponse;
import com.merchant.demo.xml.lpb.soap.res.ResponseEnvelope;

import lombok.Data;

@Data
public class OtpConfirmResponse  {
	
	@JsonProperty("ResponseHeader")
	private ResponseHeader responseHeader;
	@JsonProperty("BccardOnlineRequestBody")
	private OtpConfirmResponseVo otpConfirmResponseVo;
	@JsonProperty("Signature")
	private String signature;
	
	public boolean veryfySignature() throws Exception {
		String sigData = LpbMsg.makeSignedData(responseHeader, otpConfirmResponseVo);
		return Ciphers.verifyRsaSignature(sigData, signature, Ciphers.lpbDevPublicKey);
	}
	
	public static OtpConfirmResponse openEnvelope(ResponseEnvelope r) throws Exception {
		String data = r.getBody().getExecuteResponse().decryptedResponseData();
		return XmlUtils.unmarshalXml(data, OtpConfirmResponse.class);
	}
	
}
