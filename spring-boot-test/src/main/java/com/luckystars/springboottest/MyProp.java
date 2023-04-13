package com.luckystars.springboottest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component()
public class MyProp {

    @Value("${my.testprop}")
    private String testprop;

    public String getTestprop() {
        return testprop;
    }

    public void setTestprop(String testprop) {
        this.testprop = testprop;
    }
}
