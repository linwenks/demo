package com.linw.demotest.lock;

/**
 * 非公平，悲观，独享，互斥，可重入的重量级锁
 */
public class SynchronizedTest {

    public synchronized void test() {
        test2();
    }

    public synchronized void test2() {

    }


}
