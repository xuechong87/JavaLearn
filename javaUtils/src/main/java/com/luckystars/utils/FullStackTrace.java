package com.luckystars.utils;

import org.junit.Test;

import java.io.PrintWriter;
import java.io.StringWriter;

public class FullStackTrace {

    public String getStackTrace(Throwable e){
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }

    @Test
    public void test(){
        System.out.println(getStackTrace(new RuntimeException()));
    }


}
