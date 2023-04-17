package com.luckystars.springboottest.controller;

import com.luckystars.dubboapi.TestApi;
import com.luckystars.springboottest.MyProp;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {

    @Autowired
    private MyProp myProp;

    @Reference(url="dubbo://192.168.0.153:20880/com.luckystars.dubboapi.TestApi")
    private TestApi testApiImpl;

    @RequestMapping("/test/{text}")
    String home( @PathVariable("text") String input) {

        String result = testApiImpl.hello(input);
        System.out.println();

        return myProp.getTestprop() + result;
    }
}
