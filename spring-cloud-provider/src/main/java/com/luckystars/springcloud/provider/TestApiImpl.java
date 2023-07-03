package com.luckystars.springcloud.provider;

import com.luckystars.dubboapi.TestApi;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Value;


@DubboService
public class TestApiImpl implements TestApi {

    @Value("${dubbo.application.name}")
    private String serviceName;

    public String hello(String input) {
        System.out.println("SERVICE :" + serviceName
                + "| [com.luckystars.springcloud.provider.TestApiImpl] received:"
                + input);

        return "hello " + input;
    }
}
