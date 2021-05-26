package com.merchant.demo.log;

public enum InOutDivision {

	INBOUND(1),
	OUTBOUND(2),
	;
	
	private int code;
	
	private InOutDivision(int code) {
		this.code = code;
	}
	
	public int intValue() {
		return code;
	}
	
	public InOutDivision reverse() {
		if (this == INBOUND) {
			return OUTBOUND;
		} else  {
			return INBOUND;
		}
	}
	
	public static InOutDivision valueOf(int code) {
		switch(code) {
		case 1:
			return INBOUND;
		case 2:
			return OUTBOUND;
		
		default:
			throw new IllegalArgumentException("unknown code: " + code);
		}
	}
	
	
	
}
