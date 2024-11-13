package com.luckystars.tests;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    int val = 0;
    ReentrantLock lock = new ReentrantLock();

    public void write(){
        lock.lock();
        try {
            val ++;
        }finally {
            lock.unlock();
        }

    }
}
