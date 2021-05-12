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
import com.merchant.demo.xml.lpb.api.otpRequest.OnlineRequestResponse;
import com.merchant.demo.xml.lpb.soap.req.RequestBody;
import com.merchant.demo.xml.lpb.soap.req.RequestEnvelope;
import com.merchant.demo.xml.lpb.soap.res.ResponseEnvelope;

class TestLpbXml {
	
	@Test
	public void test() throws JsonProcessingException {
		
		System.out.println("test");
		
		RequestEnvelope en = new RequestEnvelope();
		RequestBody body = new RequestBody();
		com.merchant.demo.xml.lpb.soap.req.Execute exe = new com.merchant.demo.xml.lpb.soap.req.Execute();
		exe.setUser("Bccard");
		exe.setRequestKey("testKet");
		exe.setRequestData("testData");
		body.setExecute(exe);
		en.setBody(body);
		

		XmlMapper xmlMapper = new XmlMapper();
		String xml = xmlMapper.writeValueAsString(en);
		System.out.println(xml);
		assertNotNull(xml);
	}
	
	@Test
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
	public void whenJavaGotFromXmlFile_thenCorrect() throws IOException {
	    File file = new File("response.xml");
	    XmlMapper xmlMapper = new XmlMapper();
	    String xml = inputStreamToString(new FileInputStream(file));
	    ResponseEnvelope value = xmlMapper.readValue(xml, ResponseEnvelope.class);
	    System.out.println(value.toString());
	}
	
	@Test
	public void whenJavaGotFromXmlFile_thenCorrect2() throws IOException {
	    File file = new File("testRes.xml");
	    XmlMapper xmlMapper = new XmlMapper();
	    String xml = inputStreamToString(new FileInputStream(file));
	    OnlineRequestResponse value = xmlMapper.readValue(xml, OnlineRequestResponse.class);
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
