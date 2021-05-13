package com.merchant.demo.xml.lpb.api.otpConfirm.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.merchant.demo.xml.lpb.api.comm.LpbMsg;

import lombok.Data;

@Data
public class OtpConfirmResponseVo implements LpbMsg {
	@JsonProperty("RsltCode")
	@LpbField(signature = true) 
	private String rsltCode;
	
	@JsonProperty("RsltMsg")
	@LpbField(signature = false) 
	private String rsltMsg;
	
	@JsonProperty("WalletId")
	@LpbField(signature = true) 
	private String walletId;
	
	@JsonProperty("DataMappVal")
	@LpbField(signature = true) 
	private String dataMappVal;

	
}
