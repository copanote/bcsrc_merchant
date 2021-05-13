package com.merchant.demo.xml.lpb.api.otpConfirm;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.merchant.demo.xml.lpb.api.comm.RequestHeader;
import com.merchant.demo.xml.lpb.api.otpConfirm.vo.OtpConfirmVo;

import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "BccardOnlineRequest")
public class OtpConfirm  {
	@JsonProperty("RequestHeader")
	private RequestHeader requestHeader;
	@JsonProperty("BccardOnlineRequestBody")
	private OtpConfirmVo otpConfirm;
	@JsonProperty("Signature")
	private String signature;

}
