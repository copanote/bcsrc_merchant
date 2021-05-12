package com.merchant.demo.xml.lpb.soap.res;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ResponseBody {
	@JsonProperty("executeResponse")
	private ExecuteResponse executeResponse;
}
