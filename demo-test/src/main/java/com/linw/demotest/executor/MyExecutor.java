package com.linw.demotest.executor;

import java.util.concurrent.*;

/**
 * https://www.cnblogs.com/superfj/p/7544971.html
 */
public class MyExecutor {

    // 可重用 固定线程数 共享的无界队列 LinkedBlockingQueue
    public static ExecutorService ftp = Executors.newFixedThreadPool(10);

    // 可缓存 如果线程池长度超过处理需要，可灵活回收空闲线程 阻塞队列 SynchronousQueue
    public static ExecutorService ctp = Executors.newCachedThreadPool();

    // 定长 支持定时及周期性任务执行 LinkedBlockingQueue
    public static ExecutorService ste = Executors.newSingleThreadExecutor();

    // 单线程 它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行 DelayedWorkQueue
    public static ExecutorService stp = Executors.newScheduledThreadPool(10);

    /*
    1）有界任务队列ArrayBlockingQueue：基于数组的先进先出队列，此队列创建时必须指定大小；

    2）无界任务队列LinkedBlockingQueue：基于链表的先进先出队列，如果创建时没有指定此队列大小，则默认为Integer.MAX_VALUE；

    3）直接提交队列synchronousQueue：这个队列比较特殊，它不会保存提交的任务，而是将直接新建一个线程来执行新来的任务。
    */

    // 创建等待队列
    public static BlockingQueue<Runnable> bqueue = new ArrayBlockingQueue<Runnable>(20);
    // 创建线程池，池中保存的线程数为3，允许的最大线程数为5
    public static ThreadPoolExecutor pool = new ThreadPoolExecutor(3,5,50,TimeUnit.MILLISECONDS, bqueue);

    public static void main(String[] args) throws InterruptedException {
        //顺次地关闭ExecutorService,停止接收新的任务，等待所有已经提交的任务执行完毕之后，关闭ExecutorService
        ftp.shutdown();
        //阻止等待任务启动并试图停止当前正在执行的任务，停止接收新的任务，返回处于等待的任务列表
        ftp.shutdownNow();
        //判断线程池是否已经关闭
        ftp.isShutdown();
        //如果关闭后所有任务都已完成，则返回 true。注意，除非首先调用 shutdown 或 shutdownNow，否则 isTerminated 永不为 true。
        ftp.isTerminated();
        //等待（阻塞）直到关闭或最长等待时间或发生中断,timeout - 最长等待时间 ,unit - timeout 参数的时间单位  如果此执行程序终止，则返回 true；如果终止前超时期满，则返回 false
        ftp.awaitTermination(1000, TimeUnit.SECONDS);
        //提交一个返回值的任务用于执行，返回一个表示任务的未决结果的 Future。该 Future 的 get 方法在成功完成时将会返回该任务的结果。
//        ftp.submit(Callable<T> task);
        //提交一个 Runnable 任务用于执行，并返回一个表示该任务的 Future。该 Future 的 get 方法在成功完成时将会返回给定的结果。
//        ftp.submit(Runnable task, T result);
        //提交一个 Runnable 任务用于执行，并返回一个表示该任务的 Future。该 Future 的 get 方法在成功 完成时将会返回 null
//        ftp.submit(Runnable task);
        //执行给定的任务，当所有任务完成时，返回保持任务状态和结果的 Future 列表。返回列表的所有元素的 Future.isDone() 为 true。
//        ftp.invokeAll(Collection<? extends Callable<T>> tasks);
        //执行给定的任务，当所有任务完成时，返回保持任务状态和结果的 Future 列表。返回列表的所有元素的 Future.isDone() 为 true。
//        ftp.invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit);
        //执行给定的任务，如果在给定的超时期满前某个任务已成功完成（也就是未抛出异常），则返回其结果。一旦正常或异常返回后，则取消尚未完成的任务。
//        ftp.invokeAny(Collection<? extends Callable<T>> tasks);
//        ftp.invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit);


        /*

        corePoolSize:线程池核心线程数量
        如果池中线程数量少于核心线程池数量，则直接新建线程处理当前任务。
        核心线程池空闲不会被回收。
        当池中无空闲线程时，新任务将被添加到阻塞队列

        maximumPoolSize：线程池最大线程数量
        当阻塞队列已满，并且有新任务还在入队时，创建新的线程处理，直到线程数大于maximumPoolSize。
        超出corePoolSize部分的线程超过空闲时间后会被回收
        当线程已经超出corePoolSize，并且队列容量已满，则拒绝入队。

        keepAliveTime unit：线程存活时间
                当线程超出corePoolSize时生效
        线程空余keepAliveTime后，将被回收

        workQueue：线程使用阻塞队列

        threadFactory：创建线程池工厂
                用于控制创建线程或者销毁线程时加入其它逻辑

        handler：线程池拒绝策略
        直接丢弃（DiscardPolicy）
        丢弃队列中最老的任务(DiscardOldestPolicy)。
        抛异常(AbortPolicy)
        将任务分给调用线程来执行(CallerRunsPolicy)

        */
    }
}
