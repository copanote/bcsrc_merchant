package com.merchant.demo.xml.lpb.soap.req;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.Data;

@Data
public class RequestBody {
	@JacksonXmlProperty(namespace = "http://function.ws.core.gateway.com/", localName = "execute")
	private Execute execute;
}
