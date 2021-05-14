package com.merchant.demo.xml.lpb.soap.req;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.Data;

@Data
@JacksonXmlRootElement(namespace = "http://schemas.xmlsoap.org/soap/envelope/", localName = "soap:Envelope")
public class RequestEnvelope {
	
	public RequestEnvelope(RequestBody body) {
		this.body = body;
	}

	@JacksonXmlProperty(namespace = "http://schemas.xmlsoap.org/soap/envelope/", localName = "soap:Body")
	private RequestBody body;
	
	public static RequestEnvelope of(String user, String data) throws Exception {
		RequestBody body = RequestBody.of(user, data);
		return new RequestEnvelope(body);
	}
	
}
