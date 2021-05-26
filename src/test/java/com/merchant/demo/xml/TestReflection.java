package com.merchant.demo.xml;

import static org.junit.Assert.assertNotNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.merchant.demo.xml.lpb.api.comm.LpbMsg;
import com.merchant.demo.xml.lpb.api.comm.header.RequestHeader;
import com.merchant.demo.xml.lpb.api.otpConfirm.vo.OtpConfirmResponseVo;
import com.merchant.demo.xml.lpb.api.otpRequest.OtpRequest;
import com.merchant.demo.xml.lpb.api.otpRequest.OtpRequestResponse;
import com.merchant.demo.xml.lpb.api.otpRequest.vo.OtpRequestVo;
import com.merchant.demo.xml.lpb.soap.req.RequestEnvelope;
import com.merchant.demo.xml.lpb.soap.res.ResponseEnvelope;

class TestReflection {

//	@Test
	void test() {
		OtpConfirmResponseVo v = new OtpConfirmResponseVo();
		v.setRsltCode("res");
		v.setDataMappVal("dd");
		v.setRsltMsg("resMsg");
		v.setWalletId("Wallet");
		
		System.out.println(LpbMsg.makeSignedData(v));

	}
	
//	@Test
	void testReq() throws Exception {
//		
//		RequestHeader header = new RequestHeader();
//		header.setChannelType("channelType");
//		header.setCommand("Command");
//		header.setPassword("bcc");
//		header.setRequestDateTime("202110101010");
//		header.setSystemTraceId("traceID");
//		
//		OtpRequestVo reqVo = new OtpRequestVo();
//		reqVo.setCurrencyCode("410");
//		reqVo.setMerchantName("BCCARD");
//		reqVo.setMobileNo("01089733575");
//		reqVo.setOtherInfo("others");
//		reqVo.setProductInfo("product");
//		reqVo.setPurchaseAmount("1000");
//		
//		OtpRequest req = new OtpRequest();
//		
//		req.setRequestHeader(header);
//		req.setOtpRequestVo(reqVo);
////		req.setSignature(req.createSignature());
//		
//		System.out.println(req.toString());
//		
//		XmlMapper xmlMapper = new XmlMapper();
//		String xml = xmlMapper.writeValueAsString(req);
//		System.out.println(xml);
//		
//		RequestEnvelope envelopoe = RequestEnvelope.of("bccard", xml);
//		
//		System.out.println(envelopoe);
//		System.out.println(xmlMapper.writeValueAsString(envelopoe));

	}
	
	@Test
	public void whenJavaGotFromXmlFile_thenCorrect2() throws Exception {
	    File file = new File("response.xml");
	    XmlMapper xmlMapper = new XmlMapper();
	    String xml = inputStreamToString(new FileInputStream(file));
	    ResponseEnvelope value = xmlMapper.readValue(xml, ResponseEnvelope.class);
//	    System.out.println(value.toString());
	    
	    String decData = value.getBody().getExecuteResponse().decryptedResponseData();
	    OtpRequestResponse otpRequestResponse = xmlMapper.readValue(decData, OtpRequestResponse.class);
	    System.out.println(otpRequestResponse);
	    System.out.println(otpRequestResponse.veryfySignature());
	}
	
	
	
	public String inputStreamToString(InputStream is) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    String line;
	    BufferedReader br = new BufferedReader(new InputStreamReader(is));
	    while ((line = br.readLine()) != null) {
	        sb.append(line);
	    }
	    br.close();
	    return sb.toString();
	}

}
