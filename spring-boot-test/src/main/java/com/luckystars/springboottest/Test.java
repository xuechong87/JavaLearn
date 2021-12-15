package com.luckystars.springboottest;

import sun.misc.Regexp;

import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {

        String[] d1={"1.0","1","-1.00","-1","0.0","0.1","9999999999999999999999999","abc","1.999"};

        Pattern reg = Pattern.compile("^((-?[1-9]\\d{0,14})||-?[0-9])(\\.\\d{1,2})$");

        for (int i = 0; i < d1.length; i++) {
            System.out.println(d1[i] + reg.matcher(d1[i]).matches());
        }
    }
}
