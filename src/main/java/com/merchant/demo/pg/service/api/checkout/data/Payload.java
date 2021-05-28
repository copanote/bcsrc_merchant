package com.merchant.demo.pg.service.api.checkout.data;

import lombok.Data;

@Data
public class Payload {
	private String srcCorrelationId;
	private String cardNumber;
	private String expiryMonth;
	private String expiryYear;
	private String track2Data;
	private String cavv;
	private String eci;

}
