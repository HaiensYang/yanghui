package com.smh.aop.impl;

import com.smh.aop.Monitor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

/**
 * Created by hui10.yang on 18/10/10.
 */
public class ControlIntroduce extends DelegatingIntroductionInterceptor implements Monitor {

    private ThreadLocal<Boolean> map= new ThreadLocal<>();

    @Override
    public void setMonitorActive(boolean active) {
        map.set(active);
    }


    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        Object o = null;
        if (map.get() != null && map.get()) {
            System.out.println("增强前");
            o = super.invoke(mi);
            System.out.println("增强后");
        } else {
            o = super.invoke(mi);
        }
        return o;
    }

}
