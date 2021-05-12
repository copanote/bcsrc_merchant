package com.merchant.demo.xml.lpb.soap.res;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ResponseEnvelope {
	@JsonProperty("Body")
	private ResponseBody body;

}
