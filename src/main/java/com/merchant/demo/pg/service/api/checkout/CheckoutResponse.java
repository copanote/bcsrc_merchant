package com.merchant.demo.pg.service.api.checkout;

import com.merchant.demo.pg.service.api.checkout.data.Payload;

import lombok.Data;

@Data
public class CheckoutResponse {
	private String resultCode;
	private Payload payload;

}
