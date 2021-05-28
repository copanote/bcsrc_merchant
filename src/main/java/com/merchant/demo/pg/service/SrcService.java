package com.merchant.demo.pg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.merchant.demo.pg.service.api.checkout.Checkout;
import com.merchant.demo.pg.service.api.checkout.CheckoutResponse;
import com.merchant.demo.pg.service.api.http.BcSrcHttpService;

@Service
public class SrcService {
	
	@Autowired private BcSrcHttpService srcHttpService;
	
	public static final String CLIENT_ID = "c2090bfb-975a-4c81-9dd0-aa2903b9ecb0";
	public static final String CLIENT_SECRET= "mZCNK/MdcXt+oRmJHR9kGAZKWXbrIByxYF1tD9s9lKs=";
	
	public String checkout(Checkout c) {
		 CheckoutResponse res =  srcHttpService.checkoutReal(c, CLIENT_ID, CLIENT_SECRET);
		 return res.toString();
	}
	
	
	public String checkoutMock(Checkout c) {
		 CheckoutResponse res =  srcHttpService.checkoutMock(c, CLIENT_ID, CLIENT_SECRET);
		 return res.toString();
	}
}
