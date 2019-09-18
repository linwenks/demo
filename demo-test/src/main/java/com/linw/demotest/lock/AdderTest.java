package com.linw.demotest.lock;

import java.util.concurrent.atomic.LongAdder;

/**
 * 效率高于 Atomic
 * 高并发获取结果可能不准
 *
 * add()：增加指定的数值；
 * increament()：增加1；
 * decrement()：减少1；
 * intValue()/floatValue()/doubleValue()：得到最终计数后的结果
 * sum()：求和，得到最终计数结果
 * sumThenReset()：求和得到最终计数结果，并重置value。
 *
 */
public class AdderTest {

    private static LongAdder longAdder = new LongAdder();

    public static void main(String[] args) {

        longAdder.increment();
        longAdder.add(1);

        System.out.println(longAdder.sum());
        System.out.println(longAdder.longValue());
    }
}
