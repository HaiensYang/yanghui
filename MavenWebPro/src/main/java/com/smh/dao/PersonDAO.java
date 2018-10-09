package com.smh.dao;

import com.smh.entity.Person;

import java.util.List;

/**
 * Created by hui10.yang on 18/9/27.
 */
public interface PersonDAO {
    void addPerson(Person person);

    void deletePerson(Person person);
}

