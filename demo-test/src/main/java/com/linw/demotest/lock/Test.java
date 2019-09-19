package com.linw.demotest.lock;

/**
 * https://www.cnblogs.com/chinaifae/articles/10338547.html
 *
 * https://blog.csdn.net/qq_41931837/article/details/82314478
 *
 * 1.公平锁 / 非公平锁
 * 2.可重入锁 / 不可重入锁
 * 3.独享锁 / 共享锁
 * 4.互斥锁 / 读写锁
 * 5.乐观锁 / 悲观锁
 * 6.分段锁
 * 7.偏向锁 / 轻量级锁 / 重量级锁
 * 8.自旋锁
 */
public class Test {

    /*
        6 分段锁
        分段锁其实是一种锁的设计，并不是具体的一种锁，对于ConcurrentHashMap而言，其并发的实现就是通过分段锁的形式来实现高效的并发操作。我们以ConcurrentHashMap来说一下分段锁的含义以及设计思想，ConcurrentHashMap中的分段锁称为Segment，它即类似于HashMap（JDK7与JDK8中HashMap的实现）的结构，即内部拥有一个Entry数组，数组中的每个元素又是一个链表；同时又是一个ReentrantLock（Segment继承了ReentrantLock)。

        当需要put元素的时候，并不是对整个hashmap进行加锁，而是先通过hashcode来知道他要放在那一个分段中，然后对这个分段进行加锁，所以当多线程put的时候，只要不是放在一个分段中，就实现了真正的并行的插入。

        但是，在统计size的时候，可就是获取hashmap全局信息的时候，就需要获取所有的分段锁才能统计。分段锁的设计目的是细化锁的粒度，当操作不需要更新整个数组的时候，就仅仅针对数组中的一项进行加锁操作。
     */
}
