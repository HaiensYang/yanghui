package com.smh.aop.impl;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.MethodBeforeAdvice;

/**
 * Created by hui10.yang on 18/10/10.
 */
public class RoundAdvice implements MethodInterceptor{
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Object[] args = methodInvocation.getArguments();
        int a = (int) args[0];
        System.out.println("事前 " + a);
        Object o= methodInvocation.proceed();
        System.out.println("事后");
        return o;
    }
}
