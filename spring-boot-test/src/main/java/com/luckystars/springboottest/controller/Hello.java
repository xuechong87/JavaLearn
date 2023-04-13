package com.luckystars.springboottest.controller;

import com.luckystars.springboottest.MyProp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {

    @Autowired
    MyProp myProp;

    @RequestMapping("/")
    String home() {

        return myProp.getTestprop();
    }
}
