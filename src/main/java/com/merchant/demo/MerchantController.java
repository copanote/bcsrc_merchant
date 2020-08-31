package com.merchant.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MerchantController {
	
    @RequestMapping(value = "/merchant")	
    public ModelAndView merchant() {
		return new ModelAndView("merchant");
	}
    
    @RequestMapping(value = "/test")	
    public ModelAndView test() {
		return new ModelAndView("test");
	}


}
