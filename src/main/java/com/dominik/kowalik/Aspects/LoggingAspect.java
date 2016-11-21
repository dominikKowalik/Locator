package com.dominik.kowalik.Aspects;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by dominik on 2016-11-18.
 */
@Aspect
@Component
public class LoggingAspect {
   private Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Pointcut("(within(com.dominik.kowalik.web.*))")
    public void allMethods(){}

    @Before("allMethods()")
    public void logBefor(JoinPoint joinPoint){
        logger.info("Method Execution " + joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut = "allMethods()", returning = "returnedValue")
    public void logAfterReturning(Object returnedValue){
        logger.info("Returnig value " + returnedValue);
    }

    @After("allMethods()")
    public void logAfterReturning(JoinPoint joinPoint){
        logger.info("Returned value " + joinPoint.getSignature().getName());
    }
}
