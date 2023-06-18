package com.yadev.spring.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class CommonPointcuts {

    @Pointcut("@within(org.springframework.stereotype.Controller)")
    public void isController() {
    }

    @Pointcut("@within(org.springframework.stereotype.Service)")
    public void isService() {
    }
//    @Pointcut("isService() && ")
//
//    public void isFindAllMethod() {
//    }
}
