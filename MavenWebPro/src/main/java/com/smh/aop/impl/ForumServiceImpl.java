package com.smh.aop.impl;

import com.smh.aop.ForumService;

/**
 * Created by hui10.yang on 18/10/9.
 */
public class ForumServiceImpl implements ForumService{
    @Override
    public void removeForum(int a ,String name) {
        System.out.println("模拟删除 "+a + " name "+name);
//        //抛异常时增强
//        throw new RuntimeException("运行时异常");
    }

    @Override
    public void removeTopic(int b) {
        System.out.println("模拟删除" +b);
    }
}
