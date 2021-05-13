package com.merchant.demo.xml.lpb.api.comm;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;


/*
 * marker interface
 */
public interface LpbMsg {
	public static final String SIGNATURE_DELIMITER = "|";
	
	/**
	 * 
	 * @param <T>
	 * @param instance
	 * @return signature data for Lpb api
	 */
	public static <T extends LpbMsg> String makeSignature(T instance) {
		
		StringBuilder sb = new StringBuilder();
		
		Field[] fields = instance.getClass().getDeclaredFields();
		Arrays.stream(fields)
//		Arrays.asList(fields).stream()
		.filter((f) -> f.isAnnotationPresent(LpbField.class) && f.getAnnotation(LpbField.class).signature())
//		.sorted((f1,f2) -> f1.getName().compareTo(f2.getName()))
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
	
	public static  <T extends LpbMsg> String makeFullSignatureData(T header, T body) {
		String dataHeader = makeSignature(header);
		System.out.println(dataHeader);
		String dataBody = makeSignature(body);
		System.out.println(dataBody);
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



