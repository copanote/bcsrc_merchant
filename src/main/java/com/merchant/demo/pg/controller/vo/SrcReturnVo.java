package com.merchant.demo.pg.controller.vo;

import lombok.Data;

@Data
public class SrcReturnVo {
	public static final String SESSION_ID = "srcCorrelationId";
	private String resultCode;
	private String resultMessage;
	private String srcCorrelationId;
	private String srciTransactionId;

}
