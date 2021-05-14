package com.merchant.demo.xml.lpb.soap.req;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.merchant.demo.xml.cipher.Ciphers;

import lombok.Data;

@Data
public class Execute {
	
	public Execute(String user, String requestData, String requestKey) {
		this.user = user;
		this.requestData = requestData;
		this.requestKey = requestKey;
	}

	@JsonProperty("User")
	private String user;
	@JsonProperty("RequestData")
	private String requestData;
	@JsonProperty("RequestKey")
	private String requestKey;
	
	public static Execute of(String user, String reqData) throws Exception {
		
		byte[] tdesKey = Ciphers.generateTDesKey();
		byte[] requestData = Ciphers.encryptTDes(tdesKey, reqData.getBytes(StandardCharsets.UTF_8));
		byte[] requestKey = Ciphers.rsaEncrypt(Base64.getDecoder().decode(Ciphers.lpbDevPublicKey), tdesKey);
		
		String encodedRequestData = new String(Base64.getEncoder().encode(requestData), StandardCharsets.UTF_8 );
		String encodedRequestKey = new String(Base64.getEncoder().encode(requestKey), StandardCharsets.UTF_8 );
		
		return new Execute(user, encodedRequestData, encodedRequestKey);
	}
	

}
