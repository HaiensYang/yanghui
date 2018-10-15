package com.smh.aop.impl;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.aop.framework.adapter.ThrowsAdviceInterceptor;

/**
 * Created by hui10.yang on 18/10/10.
 */
public class ExceptionAdvice implements ThrowsAdvice{

    public void afterThrowing(RuntimeException re) {
        re.printStackTrace();
    }

}
