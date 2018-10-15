package com.smh.aop.impl;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Created by hui10.yang on 18/10/9.
 */
public class BeforeAdvice implements MethodBeforeAdvice{
    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        Object clinetName = objects[0];
        System.out.println("beforeAdvice "+clinetName);
    }
}
