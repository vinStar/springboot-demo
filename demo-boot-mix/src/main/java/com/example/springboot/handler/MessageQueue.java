package com.example.springboot.handler;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MessageQueue {

    //通过数组的结构存储message对象
    Message[] items;

    //入队和出队元素索引位置
    int putIndex;
    int takeIndex;

    // 计数器
    int count;


    // synchronized (msg){} 代码块加锁
    // 互斥锁
    Lock lock;
    // 条件变量
    Condition notEmpty;
    Condition notFull;

    public MessageQueue(){
        this.items = new Message[50];

        this.lock = new ReentrantLock();   //这个锁和sychronize还是有些区别的，ReentrantLock 类实现了 Lock ，它拥有与synchronized 相同的并发性和内存语义，但是添加了类似轮询锁、定时锁等候和可中断锁等候的一些特性。此外，它还提供了在激烈争用情况下更佳的性能。（换句话说，当许多线程都想访问共享资源时，JVM 可以花更少的时候来调度线程，把更多时间用在执行线程上。）
        this.notEmpty = lock.newCondition();
        this.notFull = lock.newCondition();
    }

    // 加入队列
    // 生产
    public void enqueueMessage(Message msg){
        System.out.println("加入队列");

        try {
            lock.lock();

            //消息队列满了，子线程停止发送消息，阻塞
            while (count == items.length){
                try {
                    notFull.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            items[putIndex] = msg;
            //循环取值
            putIndex = (++ putIndex == items.length)?0:putIndex;
            count ++;

            //生产出来新的message对象，通知主线程
            notEmpty.signalAll();

        }finally {
            lock.unlock();
        }
    }

    // 出队列
    // 消费
    public Message next(){

        //消息队列空了，子线程停止发送消息，阻塞
        Message msg = null;
        try {
            lock.lock();

            while (count == 0){
                try {
                    notEmpty.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            msg = items[takeIndex];
            items[takeIndex] = null;   //元素重置空
            takeIndex = (++takeIndex == items.length) ? 0 : takeIndex;
            count --;

            //使用列一个message对象，通知子线程，可以继续生产
            notFull.signalAll();

        }finally {
            lock.unlock();
        }

        return msg;
    }
}
