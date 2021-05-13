package com.merchant.demo.xml.lpb.api.otpConfirm;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.merchant.demo.xml.lpb.api.comm.ResponseHeader;
import com.merchant.demo.xml.lpb.api.otpConfirm.vo.OtpConfirmResponseVo;

import lombok.Data;

@Data
public class OtpConfirmResponse  {
	
	@JsonProperty("ResponseHeader")
	private ResponseHeader responseHeader;
	@JsonProperty("BccardOnlineRequestBody")
	private OtpConfirmResponseVo otpConfirmResponseVo;
	@JsonProperty("Signature")
	private String signature;
}
