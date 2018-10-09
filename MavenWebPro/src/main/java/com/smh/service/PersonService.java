package com.smh.service;

import com.smh.entity.Person;
import com.smh.entity.User;

/**
 * Created by hui10.yang on 18/9/27.
 */
public interface PersonService {
    void savePerson(Person person);

    void insertUser(User user);
}
