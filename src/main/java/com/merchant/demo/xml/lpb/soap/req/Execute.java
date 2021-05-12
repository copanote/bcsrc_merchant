package com.merchant.demo.xml.lpb.soap.req;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Execute {
	@JsonProperty("User")
	private String user;
	@JsonProperty("ResponseData")
	private String requestData;
	@JsonProperty("RequestKey")
	private String requestKey;

}
