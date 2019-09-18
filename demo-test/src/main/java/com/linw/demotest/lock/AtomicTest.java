package com.linw.demotest.lock;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 线程安区
 *
 * 靠底层的cas来保障原子性
 * 用死循环不断地cas到特定的值
 */
public class AtomicTest {

    private static final AtomicLong al = new AtomicLong();

    public static void main(String[] args) {
        System.out.println(al.addAndGet(1));
    }
}
