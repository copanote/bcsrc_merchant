package com.merchant.demo.xml.lpb.api.comm;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.merchant.demo.xml.lpb.soap.res.ResponseEnvelope;


/*
 * marker interface
 */
public interface LpbMsg {
	public static final String SIGNATURE_DELIMITER = "|";
	
	/**
	 * 
	 * @param <T> instance
	 * @return StringData which to be signed. 
	 * @Desc
	 *   Signature making rules
	 *    1. Check LpbField annotation is present or not. And marked signature is true.
	 *    2. Sorted by alphabetical order of the T's field name
	 *    3. Joined value of T's sorted field. and separated by "|"
	 */
	public static <T extends LpbMsg> String makeSignedData(T instance) {
		
		if (instance == null) {
			return "";
		}
		
		StringBuilder sb = new StringBuilder();
		
		Field[] fields = instance.getClass().getDeclaredFields();
		Arrays.stream(fields)
		.filter((f) -> f.isAnnotationPresent(LpbField.class) && f.getAnnotation(LpbField.class).signature())
		.sorted(Comparator.comparing(Field::getName))
		.map((f) ->  {
			f.setAccessible(true);
			String ret = "";
			try {
				ret = String.valueOf(f.get(instance));
			    if (ret.equalsIgnoreCase("NULL")) ret = "";
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
				ret = "";
			}
			return ret;
		})
		.filter( s -> !s.isEmpty() )
		
		.collect(Collectors.toList())
		.forEach((s) -> {
			sb.append(s);
			sb.append(SIGNATURE_DELIMITER);
		});
		
		if (sb.length() > 0 ) {
			int lastIndexOfDelimiter = sb.lastIndexOf(SIGNATURE_DELIMITER);
			if (lastIndexOfDelimiter > -1) sb.deleteCharAt(lastIndexOfDelimiter);
		}
		return sb.toString();
	}
	
	
	/**
	 * 
	 * @param <T extends LpbMsg> header, <T extends LpbMsg> body
	 * @return StringData which to be signed. 
	 * 
	 */
	public static  <T extends LpbMsg> String makeSignedData(T header, T body) {
		if (header == null) {
			throw new IllegalArgumentException("header must not be null");
		}
		
		String dataHeader = makeSignedData(header);
		String dataBody = makeSignedData(body);
		if ( dataBody == null || dataBody.isEmpty()) {
			return dataHeader;
		} else {
			return dataHeader + SIGNATURE_DELIMITER + dataBody;
		}
	}
	
	
	@Retention(RUNTIME) @Target(FIELD)
	@interface LpbField {
		boolean signature() default false;
	}
}



