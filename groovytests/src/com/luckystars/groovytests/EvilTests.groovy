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
//        String script = "System.out.println(\"Hello World\")";
        //这里需要import DynamicClass 否则会报错
        String script2 = "import com.luckystars.groovytests.EvilTests.DynamicClass;" +
                "       DynamicClass.metaClass.printName = {println name}; \n" +
                "        DynamicClass dc = new DynamicClass();\n" +
                "        dc.name = \"阿发啊啊啊\";\n" +
                "        dc.printName();\n" +
                "        DynamicClass.metaClass.age = 10;\n" +
                "        DynamicClass.metaClass.printAge = {println age};\n" +
                "        dc.printAge();";
        new GroovyShell().evaluate(script2);
    }

}
