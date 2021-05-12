package com.merchant.demo.xml.lpb.api.otpConfirm.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class OtpConfirmResponse {
	@JsonProperty("RsltCode")
	private String rsltCode;
	@JsonProperty("RsltMsg")
	private String rsltMsg;
	@JsonProperty("WalletId")
	private String walletId;
	@JsonProperty("DataMappVal")
	private String dataMappVal;
}
