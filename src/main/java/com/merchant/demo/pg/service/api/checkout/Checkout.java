package com.merchant.demo.pg.service.api.checkout;

import com.merchant.demo.log.JsonUtils;

import lombok.Data;

@Data
public class Checkout {
	private final String srcDpaId = "c21195dc-39eb-422c-8328-db2b0b535ed3";  // static value
	private String srcCorrelationId;  
	private String srciTransactionId;
	
	public String toJSON() {
		return JsonUtils.ObjectToJson(this);
	}
}
