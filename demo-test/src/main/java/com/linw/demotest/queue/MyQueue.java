package com.linw.demotest.queue;

import java.util.PriorityQueue;
import java.util.concurrent.*;

/**
 * https://www.cnblogs.com/lemon-flm/p/7877898.html
 *
 * https://www.cnblogs.com/shangxiaofei/category/663378.html
 *
 * Collection
 * Queue
 * 阻塞:BlockingQueue \ 非阻塞
 *
 */
public class MyQueue {

    // 阻塞
    // 一个由数组支持的有界队列
    public static ArrayBlockingQueue abq = new ArrayBlockingQueue(10);

    // 一个由链接节点支持的可选有界队列。
    public static LinkedBlockingQueue lbq = new LinkedBlockingQueue();

    // 一个由优先级堆支持的无界优先级队列
    public static PriorityBlockingQueue pbq = new PriorityBlockingQueue();

    // 一个由优先级堆支持的、基于时间的调度队列
    public static DelayQueue dq = new DelayQueue();

    /**
     * 一个利用 BlockingQueue 接口的简单聚集（rendezvous）机制
     * 同步阻塞队列
      */
    public static SynchronousQueue sq = new SynchronousQueue();

    // 非阻塞
    public static PriorityQueue pq = new PriorityQueue();
    public static ConcurrentLinkedQueue clq = new ConcurrentLinkedQueue();

    public static void main(String[] args) throws InterruptedException {
        /*
        add        增加一个元索                     如果队列已满，则抛出一个IIIegaISlabEepeplian异常
        remove   移除并返回队列头部的元素    如果队列为空，则抛出一个NoSuchElementException异常
        element  返回队列头部的元素             如果队列为空，则抛出一个NoSuchElementException异常

        offer       添加一个元素并返回true       如果队列已满，则返回false
        poll         移除并返问队列头部的元素    如果队列为空，则返回null
        peek       返回队列头部的元素             如果队列为空，则返回null

        put         添加一个元素                      如果队列满，则阻塞
        take        移除并返回队列头部的元素     如果队列为空，则阻塞
        */

        boolean r1 = abq.add(1);
        boolean r2 = abq.remove(1);
        Object r3 = abq.element();

        boolean r4 = abq.offer(1);
        Object r5 = abq.poll();
        Object r6 = abq.peek();

        abq.put(1);
        Object r7 = abq.take();
    }
}
