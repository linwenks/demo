package com.linw.demotest.lock;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * synchronized 的加强版，作用是控制线程的并发数量
 *
 * 1、semaphore.acquire();
 * 请求一个信号量，这时候信号量个数-1，当减少到0的时候，下一次acquire不会再执行，只有当执行一个release()的时候，信号量不为0的时候才可以继续执行acquire
 *
 * 2、semaphore.release();
 * 释放一个信号量，这时候信号量个数+1，
 *
 * 我开启了100个线程，执行一个showLog()方法，但是我只想要所有线程中，最多有五个线程在执行该方法，其他的线程就必须排队等待。
 *
 * 则可以使用Semaphore对象进行控制，该对象new初始化的时候有个int参数，即指定最多信号量个数。
 */
public class SemaphoreTest {

    /**
     *
     * // 创建具有给定的许可数和非公平的公平设置的 Semaphore。
     * Semaphore(int permits)
     * // 创建具有给定的许可数和给定的公平设置的 Semaphore。
     * Semaphore(int permits, boolean fair)
     *
     * // 从此信号量获取一个许可，在提供一个许可前一直将线程阻塞，否则线程被中断。
     * void acquire()
     * // 从此信号量获取给定数目的许可，在提供这些许可前一直将线程阻塞，或者线程已被中断。
     * void acquire(int permits)
     * // 从此信号量中获取许可，在有可用的许可前将其阻塞。
     * void acquireUninterruptibly()
     * // 从此信号量获取给定数目的许可，在提供这些许可前一直将线程阻塞。
     * void acquireUninterruptibly(int permits)
     * // 返回此信号量中当前可用的许可数。
     * int availablePermits()
     * // 获取并返回立即可用的所有许可。
     * int drainPermits()
     * // 返回一个 collection，包含可能等待获取的线程。
     * protected Collection<Thread> getQueuedThreads()
     * // 返回正在等待获取的线程的估计数目。
     * int getQueueLength()
     * // 查询是否有线程正在等待获取。
     * boolean hasQueuedThreads()
     * // 如果此信号量的公平设置为 true，则返回 true。
     * boolean isFair()
     * // 根据指定的缩减量减小可用许可的数目。
     * protected void reducePermits(int reduction)
     * // 释放一个许可，将其返回给信号量。
     * void release()
     * // 释放给定数目的许可，将其返回到信号量。
     * void release(int permits)
     * // 返回标识此信号量的字符串，以及信号量的状态。
     * String toString()
     * // 仅在调用时此信号量存在一个可用许可，才从信号量获取许可。
     * boolean tryAcquire()
     * // 仅在调用时此信号量中有给定数目的许可时，才从此信号量中获取这些许可。
     * boolean tryAcquire(int permits)
     * // 如果在给定的等待时间内此信号量有可用的所有许可，并且当前线程未被中断，则从此信号量获取给定数目的许可。
     * boolean tryAcquire(int permits, long timeout, TimeUnit unit)
     * // 如果在给定的等待时间内，此信号量有可用的许可并且当前线程未被中断，则从此信号量获取一个许可。
     * boolean tryAcquire(long timeout, TimeUnit unit)
     */

    private static Semaphore s = new Semaphore(2);

    // 公平锁
    private static Semaphore s2 = new Semaphore(2, true);

    static class ParkTask implements Runnable {
        private String name;

        public ParkTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                s.acquire();
                System.out.println("Thread " + this.name + " start...");
                TimeUnit.SECONDS.sleep(new Random().nextInt(10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                s.release();
            }
        }
    }

    public static void main(String[] args) {
        var pool = Executors.newCachedThreadPool();
        pool.submit(new ParkTask("1"));
        pool.submit(new ParkTask("2"));
        pool.submit(new ParkTask("3"));
        pool.submit(new ParkTask("4"));
        pool.submit(new ParkTask("5"));
        pool.submit(new ParkTask("6"));
        pool.shutdown();
    }
}
