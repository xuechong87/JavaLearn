package com.luckystars.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class Sleeps {

    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Rule
    public TestName testName = new TestName();

    @Before
    public void before(){
        System.out.println("开始测试："+testName.getMethodName());
        Long start = System.nanoTime();
        startTime.set(start);
    }

    @After
    public void after(){
        Long end = System.nanoTime();
        Long start = startTime.get();
        long dur = end - start;
        System.out.println(testName.getMethodName() + "耗时："+dur/1000000L+"ms");
    }

    @Test
    public void park(){
        LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(1)); // 挂起1秒
    }

    @Test
    public void unPark(){
        Thread curThread = Thread.currentThread();
        new Thread(()->{
            LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(2));
            LockSupport.unpark(curThread); // 挂起1秒
        }).start();
        LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(10));

    }



}
