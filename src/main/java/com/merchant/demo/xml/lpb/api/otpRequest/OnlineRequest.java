package com.merchant.demo.xml.lpb.api.otpRequest;

import com.merchant.demo.xml.lpb.api.comm.RequestHeader;
import com.merchant.demo.xml.lpb.api.otpRequest.vo.OtpRequest;

import lombok.Data;

@Data
public class OnlineRequest {
	private RequestHeader requestHeader;
	private OtpRequest otpRequest;
	private String signature;
	
}
