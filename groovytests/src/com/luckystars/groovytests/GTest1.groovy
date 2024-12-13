package com.luckystars.groovytests

import org.apache.commons.lang3.StringUtils
import org.junit.Test

class GTest1 {
    static void main(args) {
        def st = format("Hello World");
        println(st)

        def format2 = {str ->"{{${str}}}"}
        println(format2("Hello World"))
    }

    static def format(str){
        return "[${str}]";
    }

    @Test
    def void test1(){
        def count = 10;
        for (x in 0..count){
            println(x)
        }
        def list = ['苹果', '香蕉', '橙子']
        for (item in list) {
            println "水果: $item"
        }
        def map = [name: '张三', age: 30, city: '北京']
        for (entry in map) {
            println "键: ${entry.key}, 值: ${entry.value}"
        }


    }

    def static padZeroG(num,length){
        def str = num.toString();
        def indexOfDot = str.indexOf(".")
        def decimalLength = indexOfDot>=0? str.length() - indexOfDot : 0;
        return StringUtils.leftPad(str, length+decimalLength, '0');
    }

    @Test
    def void testPadZero() {
        System.out.println(padZeroG(new BigDecimal("93.333"), 4));
    }

    @Test
    void collectTests(){
        def list = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
        def list2 = list.collect {x->x*x}
        print(list2)
        print(list2.class)
    }

    void test33(){

    }





}
