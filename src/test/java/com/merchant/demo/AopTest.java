package com.merchant.demo;

import org.junit.jupiter.api.Test;

import com.merchant.demo.log.LogService;

class AopTest {

	@Test
	void test() {
		LogService s = new LogService();
		s.service();
		
	}

}
