package com.merchant.demo.pg.service.api.http;

import java.io.IOException;
import java.util.UUID;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.merchant.demo.log.JsonUtils;
import com.merchant.demo.pg.service.api.checkout.Checkout;
import com.merchant.demo.pg.service.api.checkout.CheckoutResponse;
import com.merchant.demo.pg.service.api.checkout.data.Payload;

@Component
/*
 *  bcsrc api의 transport layer의 역할만 수행.  즉 http 관련 작업만 수행해서 service(Application layer)로 넘긴다.
 */
public class BcSrcHttpService {
	@Autowired CloseableHttpClient httpClient;
	
	public static final String BASE_URL = "https://isrnd.bccard.com:56443/srcdev/";
	public static final String CHECKOUT = "payments/credentials";
	
	public CheckoutResponse checkoutReal(Checkout c, String clientId, String clientSecret) {
		HttpPost post = new HttpPost(BASE_URL + CHECKOUT);
		post.addHeader("X-BcSrc-Client-Id", clientId);
		post.addHeader("X-BcSrc-Client-Secet", clientSecret);
		String res = "";
		try {
			res = sendHttp(post, c.toJSON());
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("src api fail", e);
		}
		CheckoutResponse checkoutResponse = JsonUtils.JsonToObject(res, CheckoutResponse.class);
		return checkoutResponse;
	}
	
	
	public CheckoutResponse checkoutMock(Checkout c, String clientId, String clientSecret) {
		HttpPost post = new HttpPost(BASE_URL + CHECKOUT);
		post.addHeader("X-BcSrc-Client-Id", clientId);
		post.addHeader("X-BcSrc-Client-Secet", clientSecret);
		
		CheckoutResponse checkoutResponse =  new CheckoutResponse();
		checkoutResponse.setResultCode("0000");
		Payload p = new Payload();
		p.setCardNumber("0000111122223333");
		p.setCavv("cavv");
		p.setEci("05");
		p.setExpiryMonth("9912");
		p.setSrcCorrelationId(UUID.randomUUID().toString());
		p.setTrack2Data("track2");
		
		checkoutResponse.setPayload(p);
		
		return checkoutResponse;
	}
	
	private String sendHttp(HttpPost post, String jsonMsg) throws ClientProtocolException, IOException {
		post.setEntity(new StringEntity(jsonMsg));
		
		try(CloseableHttpResponse res = httpClient.execute(post); ) {
			return EntityUtils.toString(res.getEntity(), "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	
}
