package com.merchant.demo.log;

import java.lang.reflect.Method;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Optional;
import java.util.Stack;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class LogAspect {
	
	private static final String ATTRIBUTE_NAME = "srclog";
	
//	@Around("@annotation(Loggable)")
	public void around(ProceedingJoinPoint pt) throws Throwable {
		
		String args = "";
		
		for (Object o : pt.getArgs()) {
			System.out.println(JsonUtils.ObjectToJson(o));
		}
		
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        
        pt.proceed();
        
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
		
	}
	
	
	@Before(value =  "@annotation(SrcLoggable)")
	public void before(JoinPoint jp) {
		HttpServletRequest requestt = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		Deque<DbLog> stack;
		Optional<Object> o =  Optional.ofNullable(requestt.getAttribute(ATTRIBUTE_NAME));
		DbLog log = new DbLog();

		if (o.isPresent()) {
			stack = (Deque<DbLog>) o.get();
		} else {
			//isNew
			stack = new ArrayDeque<>();
			log.setTransactionId(UUID.randomUUID().toString());
		}
		
		
		Method[] m = jp.getSignature().getDeclaringType().getDeclaredMethods();
		Optional<SrcLoggable> mm =  Arrays.stream(m)
				                     .filter(a -> a.getName().equalsIgnoreCase(jp.getSignature().getName()))
				                     .map(a -> a.getAnnotation(SrcLoggable.class))
				                     .findAny();
		
		SrcLoggable l = mm.get();
		log.setApiOffererName(l.owner());
		log.setCallApiName(l.name());
		log.setInOutDivision(l.direction());

		String logDataString = "";
		for (Object obj : jp.getArgs()) {
			logDataString += JsonUtils.ObjectToJson(obj);
		}
		log.setDbLogData(logDataString);
		stack.push(log);
		
		requestt.setAttribute(ATTRIBUTE_NAME, stack);
		System.out.println(log.toString());
		
		//TODO db save by threading
		
		
		
	}
	@AfterReturning(value ="@annotation(SrcLoggable)", returning = "result")
	public void afterReturning(JoinPoint jp, Object result)  {
		
		HttpServletRequest requestt = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		System.out.println("httpRequest After:" + requestt.getSession());
		Object obj = requestt.getAttribute(ATTRIBUTE_NAME);
		if (obj == null) return; //safe guard
		
		Deque<DbLog> s = (Deque<DbLog>) obj;
		if (s.isEmpty()) return;  //safe guard
		
		DbLog log = s.pop();
		log.setInOutDivision(log.getInOutDivision().reverse());
		log.setDbLogData(JsonUtils.ObjectToJson(result));
		System.out.println(log.toString());

		//TODO db save by threading
		
	}
//	@AfterThrowing(value ="@annotation(SrcLoggable)", throwing = "e")
	public void afterThrowing(JoinPoint jp, Throwable t) {
	}
	
	@AfterReturning(value ="@annotation(SrcAfterLoggable)", returning = "result")
	public void afterExceptionHandler(JoinPoint jp, Object result) {
		System.out.println("afterExceptionHandler");
	}
	
}
