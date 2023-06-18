package com.yadev.spring.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Pointcut("within(org.springframework.stereotype.Service)")
    public void isService() {}

    @Pointcut("execution(public * com.yadev.spring.service.*Service.findAll())")
    public void anyFindAllServiceMethod() {
    }

    @Before("anyFindAllServiceMethod()")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Entering: {}.{}() with argument[s] = {}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                joinPoint.getArgs());
    }

    @Around("anyFindAllServiceMethod() && target(service)")
    public Object addLoggingAround(ProceedingJoinPoint joinPoint, Object service) throws Throwable {
        log.info("Entering: {}.{}() with argument[s] = {}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                joinPoint.getArgs());
        try {
            Object result = joinPoint.proceed();
            log.info("RESULT. Invoked {} method in class {}, result {}", joinPoint.getSignature().getName(), service, result);
            return result;
        } catch (Throwable ex) {
            log.info("EXCEPTION. Invoked {} method in class {}, exception {}: {}", joinPoint.getSignature().getName(), service, ex.getClass(), ex.getMessage());
            throw ex;
        } finally {
            log.info("FINISH. Invoked {} method in class {}", joinPoint.getSignature().getName(), service);
        }
    }
}
