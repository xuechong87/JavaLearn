package com.luckystars.groovytests

import org.junit.Test

class EvilTests {

    class DynamicClass{
        String name;

    }

    @Test
    def void Test2(){
        DynamicClass.metaClass.printName = {println name};
        DynamicClass dc = new DynamicClass();
        dc.name = "阿发啊啊啊";
        dc.printName();
        DynamicClass.metaClass.age = 10;
        DynamicClass.metaClass.printAge = {println age};
        dc.printAge();
    }


    @Test
    def void Test1(){
        String script = "System.out.println(\"Hello World\")";
        new GroovyShell().evaluate(script);
    }

}
