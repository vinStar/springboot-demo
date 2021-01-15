package com.example.springboot.handler;


/**
 *  一个线程对应一个looper对象，一个looper对应一个消息队列
 */

public final class Looper {

    //每一个主线程都有一个looper对象
    //Looper 对象保存在threadlocal中，保证列线程数据的隔离
    static final ThreadLocal<Looper> sThreadLocal = new ThreadLocal<Looper>();

    // 一个looper对象 对应一个消息队列
    MessageQueue mQueue;

    private Looper(){
        mQueue = new MessageQueue();
    }

    //Looper对象初始化
    public static void prepare(){
        if (sThreadLocal.get()!=null){
            throw new RuntimeException("Only one Looper may be created per thread");
        }
        sThreadLocal.set(new Looper());
    }

    //获取当前线程的looper对象
    public static Looper myLooper(){
        return sThreadLocal.get();
    }

    //轮询消息队列
    public static void loop(){
        Looper me = myLooper();
        if (me == null){
            throw new RuntimeException("not Looper; Looper.prepare() wait call");
        }
        MessageQueue queue = me.mQueue;
        for (;;){
            Message msg = queue.next();
            if (msg == null){
                continue;
            }
            // 转发给handler
            msg.target.dispatchMessage(msg);
        }
    }
}
