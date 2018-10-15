package com.smh.aop;

import com.smh.aop.impl.*;
import com.smh.entity.Person;
import com.smh.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Proxy;

/**
 * Created by hui10.yang on 18/10/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"}, inheritLocations = true)
public class UnitTest {
    @Autowired
    private TargetClass targetClass;

//    @Autowired
//    private ForumService forumService;

    @Test
    public void test() {
        Person person = new Person();
        person.setId(1);
        targetClass.removePerson(person);
        User user = new User();
        user.setId(3);
        targetClass.removeUser(user);
    }

    @Test
    public void proxy() {
        ForumService forumService = new ForumServiceImpl();
        PerformanceHandler performanceHandler = new PerformanceHandler(forumService);
        ForumService proxy= (ForumService) Proxy.newProxyInstance(forumService.getClass().getClassLoader(), forumService.getClass().getInterfaces(), performanceHandler);
        proxy.removeForum(3,"yanghui");
        proxy.removeTopic(4);

    }
    @Test
    public void before() {
        ForumService forumService = new ForumServiceImpl();
        BeforeAdvice beforeAdvice = new BeforeAdvice();
        BeforeAdviceTwo beforeAdviceTwo = new BeforeAdviceTwo();
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(forumService);
        proxyFactory.addAdvice(beforeAdviceTwo);
        proxyFactory.addAdvice(beforeAdvice);
        ForumService proxy= (ForumService) proxyFactory.getProxy();
        proxy.removeForum(3,"yanghui");
    }

    @Test
    public void testAopByXML() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ForumService forumService= (ForumService) context.getBean("forum");
        forumService.removeForum(4,"lisan");
        Monitor monitor = (Monitor) forumService;
        monitor.setMonitorActive(true);
        forumService.removeForum(4,"lisan");
    }

//    @Test
//    public void testSpringAOP() {
//        forumService.removeForum(2,"lisi");
//    }
}
