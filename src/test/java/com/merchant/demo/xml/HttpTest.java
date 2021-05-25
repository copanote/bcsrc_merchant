package com.merchant.demo.xml;

import java.time.LocalDateTime;
import java.util.UUID;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

import com.merchant.demo.ApacheHttpClientConfig;
import com.merchant.demo.xml.lpb.api.comm.header.RequestHeader;
import com.merchant.demo.xml.lpb.api.otpRequest.OtpRequest;
import com.merchant.demo.xml.lpb.api.otpRequest.OtpRequestResponse;
import com.merchant.demo.xml.lpb.api.otpRequest.vo.OtpRequestVo;
import com.merchant.demo.xml.lpb.soap.res.ResponseEnvelope;

class HttpTest {
	
	

	@Test
	void lpbTest() throws Exception {
		CloseableHttpClient httpClient = ApacheHttpClientConfig.globalHttpClient();

		
		RequestHeader header = RequestHeader.of(
				                    OtpRequest.COMMAND, 
				                    "vv4bccard_test@39008", 
				                    UUID.randomUUID().toString(), 
				                    LocalDateTime.now()
				                );
		System.out.println(header);
		
		OtpRequestVo body = new OtpRequestVo();
		body.setCurrencyCode("410");
		body.setMerchantName("bccard");
		body.setMobileNo("821089733575");
		body.setOtherInfo("others");
		body.setProductInfo("book");
		body.setPurchaseAmount("1000");
		
		OtpRequest otpRequest = new OtpRequest();
		otpRequest.setRequestHeader(header);
		otpRequest.setOtpRequestVo(body);
		otpRequest.createAndSetSignature();
		
		HttpPost post = new HttpPost("https://sandbox.viviet.vn/connect/services/ViVietConnect");
		post.addHeader("SOAPAction", "urn:Execute");
		post.addHeader("Content-Type", "text/xml;charset=utf-8");
		
		String xml = otpRequest.sealEnvelope("bccard_test").toXml();
		System.out.println(otpRequest.toXml());
		System.out.println(xml);
		;
		post.setEntity(new StringEntity(xml));
		
		
		
		CloseableHttpResponse res = httpClient.execute(post);
		String resXML = EntityUtils.toString(res.getEntity(), "utf-8");
		System.out.println(resXML);
		
		
		ResponseEnvelope responseEnvelope = ResponseEnvelope.unmarshal(resXML);
		System.out.println(responseEnvelope);
		OtpRequestResponse otpRequestResponse = OtpRequestResponse.openEnvelope(responseEnvelope);
		System.out.println("signature::" +  otpRequestResponse.veryfySignature());
		
		System.out.println(otpRequestResponse.toXml());
		

		
		
		
	}

}
