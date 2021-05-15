package com.merchant.demo.xml.lpb.api.comm;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class XmlUtils {
	
	private static ObjectMapper xmlMapper = new XmlMapper();
	
	public static <T> T unmarshalXml(String rawData, Class<T> typeToken) throws JsonMappingException, JsonProcessingException {
	    return xmlMapper.readValue(rawData, typeToken);
	}
}
