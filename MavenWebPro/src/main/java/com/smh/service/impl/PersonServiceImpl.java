package com.smh.service.impl;

import com.smh.dao.PersonDAO;
import com.smh.dao.UserDAO;
import com.smh.entity.Person;
import com.smh.entity.User;
import com.smh.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hui10.yang on 18/9/27.
 */
@Service
public class PersonServiceImpl implements PersonService{
    @Autowired
    private PersonDAO personDAO;

    @Autowired
    private UserDAO userDAO;

    @Override
    public void savePerson(Person person) {
        personDAO.addPerson(person);
    }

    @Override
    public void insertUser(User user) {
        userDAO.insert(user);
    }
}
