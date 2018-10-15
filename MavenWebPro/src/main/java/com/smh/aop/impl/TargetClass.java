package com.smh.aop.impl;

import com.smh.dao.PersonDAO;
import com.smh.dao.UserDAO;
import com.smh.entity.Person;
import com.smh.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hui10.yang on 18/10/8.
 */
@Service
public class TargetClass {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private PersonDAO personDAO;

    public void removePerson(Person person) {
        System.out.println("removePerson" + person);
        personDAO.deletePerson(person);

    }

    public void removeUser(User user) {
        System.out.println("removeUser" + user);
        userDAO.delete(user);
    }

}
