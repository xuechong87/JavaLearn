package com.luckystars.mystarter.dubbo;

import com.luckystars.dubboapi.TestApi;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Value;


@DubboService
public class TestApiImpl implements TestApi {

    @Value("${dubbo.application.name}")
    private String serviceName;

    @Override
    public String hello(String input) {
        System.out.println("SERVICE :" + serviceName
                + "| [com.luckystars.mystarter.dubbo.TestApiImpl] received:"
                + input);

        return "hello " + input;
    }
}
