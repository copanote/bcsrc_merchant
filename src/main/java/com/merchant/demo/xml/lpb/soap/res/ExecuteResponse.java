package com.merchant.demo.xml.lpb.soap.res;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.merchant.demo.xml.cipher.Ciphers;

import lombok.Data;

@Data
public class ExecuteResponse {
	@JsonProperty("return")
	private Return ret;
	
	
	public String decryptedResponseData() throws Exception {
		byte[] bcPriKey = Base64.getDecoder().decode(Ciphers.bcPrivateKey);
		byte[] decodedBase64 = Base64.getDecoder().decode(this.ret.getResponseKey());
		byte[] tdesKey = Ciphers.rsaDecrypt(bcPriKey, decodedBase64);
		byte[] decryptedResData = Ciphers.decryptTDes(tdesKey, Base64.getDecoder().decode(this.ret.getResponseData()));
		return new String(decryptedResData, StandardCharsets.UTF_8);
	}
	
}

@Data
class Return {
	@JsonProperty("ResponseData")
	private String responseData;
	@JsonProperty("ResponseKey")
	private String responseKey;
	
}
