package com.merchant.demo.pg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.merchant.demo.pg.controller.vo.SrcReturnVo;
import com.merchant.demo.pg.service.SrcService;
import com.merchant.demo.pg.service.api.checkout.Checkout;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class PgController {
	
	@Autowired SrcService srcService;
	
	//http://localhost:7070/src/pg/rd?resultCode=0000&resultMessage=Success&srcCorrelationId=1234&srciTransactionId=5678
	@PostMapping(value = "pg/rd")
	@ResponseBody
	public String credential(SrcReturnVo inVo) {
		log.debug(inVo.toString());
		
		Checkout c = new Checkout();
		c.setSrcCorrelationId(inVo.getSrcCorrelationId());
		c.setSrciTransactionId(inVo.getSrciTransactionId());
		
		String res = srcService.checkoutMock(c);
		
		return "Result: " + res;
	}
}
