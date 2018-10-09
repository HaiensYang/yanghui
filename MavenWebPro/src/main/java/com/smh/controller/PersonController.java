package com.smh.controller;

import com.smh.entity.Person;
import com.smh.entity.User;
import com.smh.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hui10.yang on 18/9/27.
 */
@Controller
@RequestMapping("/home")
public class PersonController {
    @Autowired
    private PersonService personService;
    @RequestMapping("/index")
    public String index(){
        Person person = new Person();
        person.setName("杨辉");
        personService.savePerson(person);
        User user = new User();
        user.setName("张三");
        user.setAddr("上海市");
        user.setAge(12);
        personService.insertUser(user);
        return "home";
    }

}
