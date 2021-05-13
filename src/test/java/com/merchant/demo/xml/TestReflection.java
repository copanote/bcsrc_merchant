package com.merchant.demo.xml;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.merchant.demo.xml.lpb.api.comm.LpbMsg;
import com.merchant.demo.xml.lpb.api.comm.RequestHeader;
import com.merchant.demo.xml.lpb.api.otpConfirm.vo.OtpConfirmResponseVo;
import com.merchant.demo.xml.lpb.api.otpRequest.OtpRequest;
import com.merchant.demo.xml.lpb.api.otpRequest.vo.OtpRequestVo;

class TestReflection {

	@Test
	void test() {
		OtpConfirmResponseVo v = new OtpConfirmResponseVo();
		v.setRsltCode("res");
		v.setDataMappVal("dd");
		v.setRsltMsg("resMsg");
		v.setWalletId("Wallet");
		
		System.out.println(LpbMsg.makeSignature(v));

	}
	
	@Test
	void testReq() throws Exception {
		
		RequestHeader header = new RequestHeader();
		header.setChannelType("channelType");
		header.setCommand("Command");
		header.setPassword("bcc");
		header.setRequestDateTime("202110101010");
		header.setSystemTraceId("traceID");
		
		OtpRequestVo reqVo = new OtpRequestVo();
		reqVo.setCurrencyCode("410");
		reqVo.setMerchantName("BCCARD");
		reqVo.setMobileNo("01089733575");
		reqVo.setOtherInfo("others");
		reqVo.setProductInfo("product");
		reqVo.setPurchaseAmount("1000");
		
		OtpRequest req = new OtpRequest();
		
		req.setRequestHeader(header);
		req.setOtpRequestVo(reqVo);
		req.setSignature(req.createSignature());
		
		System.out.println(req.toString());
		
		XmlMapper xmlMapper = new XmlMapper();
		String xml = xmlMapper.writeValueAsString(req);
		System.out.println(xml);
		assertNotNull(xml);
		

	}

}
