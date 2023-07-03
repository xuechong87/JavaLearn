package com.luckystars.springcloud.provider;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@DubboComponentScan
@SpringBootApplication
public class DubboProvider {

    public static void main(String[] args) {
        SpringApplication.run(DubboProvider.class);
    }

}
