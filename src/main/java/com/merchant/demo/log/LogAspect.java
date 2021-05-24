package com.merchant.demo.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class LogAspect {
	
	@Around("@annotation(Loggable)")
	public void around(ProceedingJoinPoint pt) throws Throwable {
		
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        
        // @Loggable 애노테이션이 붙어있는 타겟 메소드를 실행
        pt.proceed();
        
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
		
	}
	@Before("@annotation(Loggable)")
	public void before(JoinPoint jp) {
		System.out.println("before in LogAspect|" + jp.toString());
	}
//	@AfterReturning
	public void afterReturning() {
	}
//	@AfterThrowing
	public void afterThrowing() {
	}
	
//	@After(value = "")
	public void after() {
		System.out.println("after in LogAspect");
		
	}

}
