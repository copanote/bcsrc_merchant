package com.merchant.demo.xml;

import org.junit.jupiter.api.Test;

import com.merchant.demo.xml.lpb.api.otpConfirm.vo.OtpConfirmResponseVo;

class TestReflection {

	@Test
	void test() {
		OtpConfirmResponseVo v = new OtpConfirmResponseVo();
		v.setRsltCode("res");
		v.setDataMappVal("dd");
		v.setRsltMsg("resMsg");
//		v.setWalletId();
		
		v.makeDataHeaderForSignature();
		
		System.out.println(v.makeDataHeaderForSignature());
	}

}
