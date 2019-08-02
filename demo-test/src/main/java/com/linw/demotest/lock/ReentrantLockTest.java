package com.linw.demotest.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 默认非公平但可实现公平的，悲观，可重入，重量级锁。
 * 互斥，独享
 */
public class ReentrantLockTest {

        /*
    lock(), 如果获取了锁立即返回，如果别的线程持有锁，当前线程则一直处于休眠状态，直到获取锁

    tryLock(), 如果获取了锁立即返回true，如果别的线程正持有锁，立即返回false

    tryLock(long timeout,TimeUnit unit)， 如果获取了锁定立即返回true，如果别的线程正持有锁，会等待参数给定的时间，在等待的过程中，如果获取了锁定，就返回true，如果等待超时，返回false；

    lockInterruptibly:如果获取了锁定立即返回，如果没有获取锁定，当前线程处于休眠状态，直到获取锁定，或者当前线程被别的线程中断
     */

    public static void main(String[] args) {
        // 可重入
        Lock lock = new ReentrantLock();
        for (int i = 1; i <= 3; i++) {
            lock.lock();
        }

        for(int i=1;i<=3;i++){
            try {

            } finally {
                lock.unlock();
            }
        }

        // 公平
        Lock lock2 = new ReentrantLock(true);


    }
}
