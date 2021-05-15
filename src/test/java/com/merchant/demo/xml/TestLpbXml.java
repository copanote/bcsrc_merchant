package com.merchant.demo.xml;

import static org.junit.Assert.assertNotNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.merchant.demo.xml.lpb.api.otpRequest.OtpRequestResponse;
import com.merchant.demo.xml.lpb.soap.req.RequestBody;
import com.merchant.demo.xml.lpb.soap.req.RequestEnvelope;
import com.merchant.demo.xml.lpb.soap.res.ResponseEnvelope;

class TestLpbXml {
	
	
//	@Test
	public void test2() throws JsonProcessingException {
		System.out.println("test");
	}
	
//	@Test
//	public void whenJavaSerializedToXmlFile_thenCorrect() throws IOException {
//	    XmlMapper xmlMapper = new XmlMapper();
//	    xmlMapper.writeValue(new File("response.xml"), new Execute());
//	    File file = new File("simple_bean.xml");
//	    System.out.println(file.toString());
//	    assertNotNull(file);
//	}
	
	@Test
	public void whenJavaGotFromXmlFile_thenCorrect() throws Exception {
	    File file = new File("response.xml");
	    XmlMapper xmlMapper = new XmlMapper();
	    String xml = inputStreamToString(new FileInputStream(file));
	    ResponseEnvelope value = ResponseEnvelope.unmarshal(xml);
	    
	    OtpRequestResponse otpRes = OtpRequestResponse.openEnvelope(value);
	    System.out.println("veryfySignature res:" + otpRes.veryfySignature());
	    
	    System.out.println("Header:" + otpRes.getResponseHeader());
	    System.out.println("Body:" +  otpRes.getBccardOnlineRequestBody());
	    
	    
	    
	    
	}
	
//	@Test
	public void whenJavaGotFromXmlFile_thenCorrect2() throws IOException {
	    File file = new File("testRes.xml");
	    XmlMapper xmlMapper = new XmlMapper();
	    String xml = inputStreamToString(new FileInputStream(file));
	    OtpRequestResponse value = xmlMapper.readValue(xml, OtpRequestResponse.class);
	    System.out.println(value.toString());
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
