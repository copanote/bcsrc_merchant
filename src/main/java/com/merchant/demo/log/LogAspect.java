package com.merchant.demo.log;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class LogAspect {
	
	@Autowired
	private HttpServletRequest req;
	
//	ServletRequestAttributes request = (ServletRequestAttributes) ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
	//
//	@Around("@annotation(Loggable)")
	public void around(ProceedingJoinPoint pt) throws Throwable {
		
		for (Object o : pt.getArgs()) {
			System.out.println(JsonUtils.ObjectToJson(o));
			System.out.println(o);
		}
		
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        
        // @Loggable 애노테이션이 붙어있는 타겟 메소드를 실행
        pt.proceed();
        
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
		
	}
	
	
	@Before(value =  "@annotation(Loggable)")
	public void before(JoinPoint jp) {
		req.setAttribute("srcloggable", true);
		System.out.println("before in LogAspect|" + jp.toString());

		for (Object o : jp.getArgs()) {
			System.out.println(JsonUtils.ObjectToJson(o));
			System.out.println(o);
		}
		
		
	}
	@AfterReturning(value ="@annotation(Loggable)", returning = "result")
	public void afterReturning(JoinPoint jp, Object result)  {
		System.out.println(	 "srcloggable: " + 	 req.getAttribute("srcloggable"));
		System.out.println(result.toString());
		System.out.println(JsonUtils.ObjectToJson(result));
	}
//	@AfterThrowing(value ="@annotation(Loggable)", throwing = "e")
	public void afterThrowing(JoinPoint jp, Throwable t) {
	}
	
	@After("@annotation(Loggable)")
	public void after(JoinPoint jp) {
		System.out.println("after in LogAspect");
	}

}
