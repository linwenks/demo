package com.linw.demotest.lock;

import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.StampedLock;

/**
 * ReentrantReadWriteLock 的增强版
 *
 * 乐观锁读 不可重入
 *
 * ReentrantReadWriteLock : 读和读---不互斥，读和写---互斥，写和写----互斥
 * StampedLock : 读锁不会阻塞写锁
 *
 * 如果线程拿到乐观锁就读和写不互斥，如果拿到悲观锁就读和写互斥
 *
 */
public class StampedLockTest {

    private static final StampedLock sl = new StampedLock();

    public static void main(String[] args) throws InterruptedException {
        // 乐观
        var stamp = sl.tryOptimisticRead();


        bug();
    }

    public static void bug() throws InterruptedException {

        var threads = new Thread[3];
        var sl = new StampedLock();

        //启动一个写线程长时间占用锁
        new Thread(() -> {
            long stamp = sl.writeLock();
            LockSupport.parkNanos(6100000000L);
            sl.unlock(stamp);
        }).start();
        Thread.sleep(100);

        //启动三个读线程
        for (int i = 0; i < 3; i++) {
            threads[i] = new Thread(() -> {
                long stamp = sl.readLock();
                System.out.println(Thread.currentThread().getName() + "获得锁.");
                sl.unlock(stamp);
            });
            threads[i].start();
        }
        //5秒后等到三个线程因写锁被占用导致挂起后进行中断
        Thread.sleep(5000);
        for (int i = 0 ; i < 3; i++) {
            threads[i].interrupt();
        }
    }
}
