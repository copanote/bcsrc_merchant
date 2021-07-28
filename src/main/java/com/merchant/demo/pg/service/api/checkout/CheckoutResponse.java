package com.merchant.demo.pg.service.api.checkout;

import com.merchant.demo.log.JsonUtils;
import com.merchant.demo.pg.service.api.checkout.data.Payload;

import lombok.Data;

@Data
public class CheckoutResponse {
	private String resultCode;
	private Payload payload;
	
	public String toJson() {
		return JsonUtils.ObjectToJson(this);
	}

}
