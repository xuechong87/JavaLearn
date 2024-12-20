package com.luckystars.utils;

import org.apache.commons.io.output.StringBuilderWriter;
import org.junit.Test;

import java.io.PrintWriter;
import java.io.StringWriter;

public class FullStackTrace {

    public String getStackTrace(Throwable e){
        StringBuilderWriter sw = new StringBuilderWriter();
        e.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }

    @Test
    public void test(){
        System.out.println(getStackTrace(new RuntimeException()));
    }


}
