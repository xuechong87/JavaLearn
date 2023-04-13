package com.luckystars.tests;

public class TestRunner {
    public static int func(){
        return func();
    }

    public static int  testRunner(int x,int y){
        if(x==0){
            return  x;
        }else {
            return y;
        }
    }

    public static void main(String[] args) {
        int result = testRunner(0,func());
        System.out.println(result);
    }
}
