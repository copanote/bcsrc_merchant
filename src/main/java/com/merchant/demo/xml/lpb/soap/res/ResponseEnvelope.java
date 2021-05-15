package com.merchant.demo.xml.lpb.soap.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.merchant.demo.xml.lpb.api.comm.XmlUtils;

import lombok.Data;

@Data
public class ResponseEnvelope {
	@JsonProperty("Body")
	private ResponseBody body;
	
	public static ResponseEnvelope unmarshal(String xmlString) throws JsonMappingException, JsonProcessingException {
	    return XmlUtils.unmarshalXml(xmlString, ResponseEnvelope.class);
	}
}
