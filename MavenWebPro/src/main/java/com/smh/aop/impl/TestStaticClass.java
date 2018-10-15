package com.smh.aop.impl;

import org.springframework.util.Assert;

/**
 * Created by hui10.yang on 18/10/15.
 */
public class TestStaticClass {
    static {
        System.out.println("hello");
    }

    private static void pr() {
        System.out.println("welcome");
    }

    public static void main(String[] args) {
        Aspect aspect =new Aspect();
        Assert.notNull(aspect,"ni");
        pr();
    }
}
