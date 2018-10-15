package com.smh.aop.impl;

import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Service;

/**
 * Created by hui10.yang on 18/10/12.
 */
@Service
@org.aspectj.lang.annotation.Aspect
public class Aspect {

    @Before(value = "execution(* com.smh.aop.*Service.*(..))")
    public void adBefore() {
        System.out.println("前置增强");
    }

}
