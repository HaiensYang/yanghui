package com.smh.aop;

import com.smh.entity.Person;
import com.smh.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by hui10.yang on 18/10/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"}, inheritLocations = true)
public class UnitTest {
    @Autowired
    private TargetClass targetClass;
    @Test
    public void test() {
        Person person = new Person();
        person.setId(1);
        targetClass.removePerson(person);
        User user = new User();
        user.setId(3);
        targetClass.removeUser(user);
    }
}
