package com.merchant.demo.xml.lpb.api.otpRequest.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.merchant.demo.xml.lpb.api.comm.LpbMsg;

import lombok.Data;

@Data
public class OtpRequestVo implements LpbMsg {
	@JsonProperty("MobileNo") 
	@LpbField(signature = true) 
	private String mobileNo;
	
	@JsonProperty("MerchantName") 
	@LpbField(signature = true) 
	private String merchantName;
	
	@JsonProperty("PurchaseAmount") 
	@LpbField(signature = true) 
	private String purchaseAmount;
	
	@JsonProperty("CurrencyCode") 
	@LpbField(signature = true) 
	private String currencyCode;
	
	@JsonProperty("ProductInfo") 
	@LpbField(signature = true) 
	private String productInfo;
	
	@JsonProperty("OtherInfo") 
	@LpbField(signature = true) 
	private String otherInfo;

}
