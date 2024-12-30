package com.dode.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class QueryLoggingAspect {
	
	private static final Logger logger = LoggerFactory.getLogger(QueryLoggingAspect.class);

    @Around("execution(* com.dode.repository.*.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;

        logger.info("Method [{}] executed in {} ms", joinPoint.getSignature(), executionTime);
        return result;
    }
}
