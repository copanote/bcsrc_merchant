package com.merchant.demo.xml.lpb.api.comm.code;

import java.util.Arrays;
import java.util.Optional;

public enum ResponseCode {
	
	
	CONNECT_0   ("0"           , "Successful"),
	CONNECT_1101("CONNECT-1101", ""),
	CONNECT_1102("CONNECT-1102", ""),
	CONNECT_1103("CONNECT-1103", ""),
	CONNECT_1104("CONNECT-1104", ""),
	CONNECT_1105("CONNECT-1105", ""),
	CONNECT_1106("CONNECT-1106", ""),
	CONNECT_1107("CONNECT-1107", ""),
	CONNECT_1108("CONNECT-1108", ""),
	CONNECT_1109("CONNECT-1109", ""),
	CONNECT_1110("CONNECT-1110", ""),
	CONNECT_1111("CONNECT-1111", ""),
	CONNECT_1112("CONNECT-1112", ""),
	CONNECT_1113("CONNECT-1113", ""),
	CONNECT_1114("CONNECT-1114", ""),
	CONNECT_1115("CONNECT-1115", ""),
	CONNECT_1116("CONNECT-1116", ""),
	CONNECT_1117("CONNECT-1117", ""),
	CONNECT_1118("CONNECT-1118", ""),
	CONNECT_1119("CONNECT-1119", ""),
	CONNECT_1120("CONNECT-1120", ""),
	CONNECT_1121("CONNECT-1121", ""),
	CONNECT_1122("CONNECT-1122", ""),
	CONNECT_1123("CONNECT-1123", ""),
	CONNECT_1124("CONNECT-1124", ""),
	CONNECT_1125("CONNECT-1125", ""),
	CONNECT_1126("CONNECT-1126", ""),
	CONNECT_1127("CONNECT-1127", ""),
	CONNECT_1128("CONNECT-1128", ""),
	
	CONNECT_1140("CONNECT-1140", ""),
	CONNECT_1141("CONNECT-1141", ""),
	CONNECT_1142("CONNECT-1142", ""),
	CONNECT_1143("CONNECT-1143", ""),
	CONNECT_1144("CONNECT-1144", ""),
	CONNECT_1145("CONNECT-1145", ""),
	CONNECT_1146("CONNECT-1146", ""),
	CONNECT_1147("CONNECT-1147", ""),
	CONNECT_1148("CONNECT-1148", ""),
	CONNECT_1149("CONNECT-1149", ""),
	CONNECT_1150("CONNECT-1150", ""),
	CONNECT_1151("CONNECT-1151", ""),
	CONNECT_1152("CONNECT-1152", ""),
	CONNECT_1153("CONNECT-1153", ""),
	CONNECT_1154("CONNECT-1154", ""),
	CONNECT_1155("CONNECT-1155", ""),
	CONNECT_1160("CONNECT-1160", ""),

	CONNECT_9998("CONNECT-9998", ""),
	CONNECT_9999("CONNECT-9999", ""),
	CONNECT_2010("CONNECT-2010", ""),
	
	UNKNOWN("Unknown", "Unknown")
	;
	
	private String code;
	private String narration;
	
	private ResponseCode(String code, String narration) {
		this.code = code;
		this.narration = narration;
	}
	
	public String getCode() {
		return code;
	}

	public String getNarration() {
		return narration;
	}

	public static ResponseCode findByCode(String code) {
		
		Optional<ResponseCode> responseCode =  
				Arrays.stream(ResponseCode.values())
		              .filter(e -> e.getCode().equalsIgnoreCase(code.trim()))
		              .findAny();
		
		if (responseCode.isPresent()) {
			return responseCode.get();
		}
		
		return UNKNOWN;
	}
}
