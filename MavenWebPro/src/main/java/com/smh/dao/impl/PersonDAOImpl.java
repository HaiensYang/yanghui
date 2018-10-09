package com.smh.dao.impl;

import com.smh.dao.PersonDAO;
import com.smh.entity.Person;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by hui10.yang on 18/9/27.
 */
@Repository
public class PersonDAOImpl implements PersonDAO{

    @Autowired
    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    @Override
    public void addPerson(Person
                                      person) {
        sessionFactory.getCurrentSession().saveOrUpdate(person);
    }

    @Transactional
    @Override
    public void deletePerson(Person person) {
        sessionFactory.getCurrentSession().delete(person);
    }
}
