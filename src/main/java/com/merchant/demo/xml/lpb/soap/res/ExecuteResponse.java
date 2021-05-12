package com.merchant.demo.xml.lpb.soap.res;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ExecuteResponse {
	@JsonProperty("return")
	private Return ret;
}

@Data
class Return {
	@JsonProperty("ResponseData")
	private String responseData;
	@JsonProperty("ResponseKey")
	private String responseKey;
}
