package com.merchant.demo.xml.lpb.soap.req;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.Data;

@Data
@JacksonXmlRootElement(namespace = "http://schemas.xmlsoap.org/soap/envelope/", localName = "Envelope")
public class RequestEnvelope {
	
	public RequestEnvelope(RequestBody body) {
		this.body = body;
	}

	@JacksonXmlProperty(namespace = "http://schemas.xmlsoap.org/soap/envelope/", localName = "Body")
	private RequestBody body;
	
	
	public String toXml() throws JsonProcessingException {
		XmlMapper xmlMapper = new XmlMapper();
		return xmlMapper.writeValueAsString(this);
	}
	
	public static RequestEnvelope of(String user, String data) throws Exception {
		RequestBody body = RequestBody.of(user, data);
		return new RequestEnvelope(body);
	}
	
}
