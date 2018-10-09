package com.smh.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by hui10.yang on 18/10/8.
 */
public class PerformanceHandler implements InvocationHandler{

    private Object target;

    public PerformanceHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object obj = method.invoke(target, args);
        return obj;
    }
}
