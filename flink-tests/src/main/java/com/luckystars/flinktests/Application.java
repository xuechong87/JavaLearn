package com.luckystars.flinktests;


import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class Application {



    //LocalStreamEnvironment 在创建它的同一个 JVM 进程中启动 Flink 系统。
    // 如果你从 IDE 启动 LocalEnvironment，则可以在代码中设置断点并轻松调试程序。


    public static void main(String[] args) {
        System.out.println("aaa");
//        final StreamExecutionEnvironment env =
//                StreamExecutionEnvironment.getExecutionEnvironment();
        final StreamExecutionEnvironment env =
                StreamExecutionEnvironment.createLocalEnvironment();



        System.out.println("aaa");

    }

}
