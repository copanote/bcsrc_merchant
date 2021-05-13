package com.merchant.demo.xml.lpb.api.otpConfirm.vo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.merchant.demo.xml.lpb.api.comm.LpbField;

import lombok.Data;

public class OtpConfirmResponseVo {
	@JsonProperty("RsltCode")
	@LpbField(signature = true) 
	private String rsltCode;
	
	@JsonProperty("RsltMsg")
	@LpbField(signature = false) 
	private String rsltMsg;
	
	@JsonProperty("WalletId")
	@LpbField(signature = true) 
	private String walletId;
	
	@JsonProperty("DataMappVal")
	@LpbField(signature = true) 
	private String dataMappVal;
	
	
	
	
	
	
public String getRsltCode() {
		return rsltCode;
	}






	public void setRsltCode(String rsltCode) {
		this.rsltCode = rsltCode;
	}






	public String getRsltMsg() {
		return rsltMsg;
	}






	public void setRsltMsg(String rsltMsg) {
		this.rsltMsg = rsltMsg;
	}






	public String getWalletId() {
		return walletId;
	}






	public void setWalletId(String walletId) {
		this.walletId = walletId;
	}






	public String getDataMappVal() {
		return dataMappVal;
	}






	public void setDataMappVal(String dataMappVal) {
		this.dataMappVal = dataMappVal;
	}






	//	
//	private static Map<Field, Method> signatureCache = new HashMap<>();
//	
//	@JsonIgnore
//	private static Map<String, Boolean> signatureMap = new HashMap<>();
//	
//	static {
//		initSignatureMap();
//	}
//	
//	
//	private static void initSignatureMap() {
//		Field[] fields = OtpConfirmResponseVo.class.getDeclaredFields();
//		Method[] methods = OtpConfirmResponseVo.class.getMethods();
//		for (Method method : methods) {
////			method.
//		}
//		
//		for (Field field : fields) {
//			field.setAccessible(true);
//			if (field.isAnnotationPresent(LpbField.class)) {
//				signatureMap.put(field.getName(), field.getAnnotation(LpbField.class).signature() );
//			}
//		}
//		
//	}
//	
//	public static Map<String, Boolean> getSignatureFields() {
//		if (signatureMap.isEmpty()) {
//			initSignatureMap();
//		}
//		return signatureMap;
//	}
//	
//	public static String makeDataHeaderForSignature2() {
//		StringBuilder sb = new StringBuilder();
//		
//		Map<String, Boolean> sigMap = getSignatureFields();
//		sigMap.forEach((s, b) -> {
//			if (b) {
//			}
//			
//		});
//		return sb.toString();
//	}
//	
	public String makeDataHeaderForSignature() {
		Field[] fields = this.getClass().getDeclaredFields();
		List<Field>  l = Arrays.asList(fields);
		List<String> dataHeaders = l.stream()
				.filter((o) -> o.isAnnotationPresent(LpbField.class) && o.getAnnotation(LpbField.class).signature())
				.sorted((o1,o2) -> o1.getName().compareTo(o2.getName()))
				.map((o) ->  {
					String ret = "";
					try {
						ret = String.valueOf(o.get(this));
					    if (ret.equalsIgnoreCase("NULL")) ret = "";
					} catch (IllegalArgumentException | IllegalAccessException e) {
						ret = "";
					}
					return ret;
				})
				.filter((s) -> !s.isEmpty() )
				.collect(Collectors.toList());
		
		StringBuilder sb = new StringBuilder();
		dataHeaders.forEach((s) -> {
			sb.append(s);
			sb.append("|");
		});
		sb.deleteCharAt(sb.lastIndexOf("|"));
		
		return sb.toString();
	}
	
	
}
