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
    /*
        构造指定容量， 并可以选择是否需要公平性，true，等待时间最长的线程会优先得到处理（其实就是通过将ReentrantLock设置为true来 达到这种公平性的：即等待时间最长的线程会先操作）
        公平性会使你在性能上付出代价，只有在的确非常需要的时候再使用它
        数组阻塞循环队列
        FIFO（先进先出）
     */
    public static ArrayBlockingQueue abq = new ArrayBlockingQueue(10);

    /*
        容量 Integer.MAX_VALUE，不要然的话在put时怎么会受阻呢
        基于链表
        FIFO（先进先出）排序元素
     */
    public static LinkedBlockingQueue lbq = new LinkedBlockingQueue();

    /*
        带优先级，而不是先进先出
        元素按优先级顺序被移除
        没有上限（对 PriorityQueue的再次包装
        堆数据结构，而PriorityQueue是没有容量限制的，与ArrayList一样，所以在优先阻塞 队列上put时是不会受阻的
        虽然逻辑上是无界的，但是由于资源被耗尽，所以试图执行添加操作可能会导致 OutOfMemoryError）
        但是如果队列为空，那么取元素的操作take就会阻塞，所以它的检索操作take是受阻的
        往入该队列中的元素要具有比较能力。
     */
    public static PriorityBlockingQueue pbq = new PriorityBlockingQueue();

    /*
        （基于PriorityQueue来实现的）是一个存放Delayed 元素的无界阻塞队列
        只有在延迟期满时才能从中提取元素
        该队列的头部是延迟期满后保存时间最长的 Delayed 元素
        如果延迟都还没有期满，则队列没有头部，并且poll将返回null。
        当一个元素的 getDelay(TimeUnit.NANOSECONDS) 方法返回一个小于或等于零的值时，则出现期满，poll就以移除这个元素了。此队列不允许使用 null 元素。

        缓存系统的设计：使用DelayQueue保存缓存元素的有效期，使用一个线程循环查询DelayQueue，一旦能从DelayQueue中获取元素时，就表示有缓存到期了。
        定时任务调度：使用DelayQueue保存当天要执行的任务和执行时间，一旦从DelayQueue中获取到任务就开始执行，比如Timer就是使用DelayQueue实现的。
     */
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
