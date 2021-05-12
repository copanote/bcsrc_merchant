package com.merchant.demo.xml.lpb.api.otpRequest.vo;

import lombok.Data;

@Data
public class OtpRequest {
	private String mobileNo;
	private String merchantName;
	private String purchaseAmount;
	private String currencyCode;
	private String productInfo;
	private String otherInfo;

}
