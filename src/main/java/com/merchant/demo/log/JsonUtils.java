package com.merchant.demo.log;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
	
	private static ObjectMapper objectMapper = new ObjectMapper();
	
	
	@Autowired 
	public JsonUtils(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	public static String ObjectToJson(Object o) {
		
		String result = null;
		try {
			result = objectMapper.writeValueAsString(o);
		} catch(JsonMappingException e ) {
			throw new IllegalArgumentException("JsonMappingException");
			
		} catch(JsonProcessingException e) {
			throw new IllegalArgumentException("JsonProcessingException");
		}
		return result;
	}
	
	public static <T> T JsonToObject(String json, Class<T> clazz) {
		T t = null;
		try {
			t = objectMapper.readValue(json, clazz);
		} catch (JsonProcessingException e) {
			throw new IllegalArgumentException("JsonProcessingException");		
		}
		
		
		return t;
	}
	
	
	public static Map<String, Object> jsonToMap(String json) {
		Map<String, Object> map;
		try {
			map = objectMapper.readValue(json, new TypeReference<Map<String,Object>>(){});
		} catch (JsonProcessingException e) {
			throw new IllegalArgumentException("JsonProcessingException");		
		}
		return map;
	}
	
	public static Map<String, Object> objectToMap(Object obj) {
		Map<String, Object> map;
		map = objectMapper.convertValue(obj, Map.class);
		
		return map;
	}
	
	public static String mapToJson(Map<String, Object> map) {
		String json;
		
		try {
			json = objectMapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			throw new IllegalArgumentException("JsonProcessingException");		
		}
		
		return json;
	}	
	
//	@SuppressWarnings("unchecked")
//	public static <T>  T  of(String jsonPayload) {
//		ObjectMapper mapper = new ObjectMapper(); // create once, reuse
//		T t = null;
//		try {
//			t = mapper.readValue(jsonPayload, (Class<T>) t);
//		} catch (JsonMappingException e) {
//			t = null;
//		} catch (JsonProcessingException e) {
//			t = null;
//		}
//		return t;
//	}
}

