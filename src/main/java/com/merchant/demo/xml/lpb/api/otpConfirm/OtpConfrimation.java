package com.merchant.demo.xml.lpb.api.otpConfirm;

import com.merchant.demo.xml.lpb.api.comm.RequestHeader;

import lombok.Data;

@Data
public class OtpConfrimation {
	private RequestHeader requestHeader;
	private OtpConfrimation otpConfrimation;
	private String signature;

}
