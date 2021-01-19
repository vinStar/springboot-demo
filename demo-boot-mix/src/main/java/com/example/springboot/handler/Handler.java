package com.example.springboot.handler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Handler {

    private MessageQueue mQueue;
    private Looper mLooper;

    // Handler的初始化在主线程中完成
    public Handler(){
        //获取主线程的looper对象
        mLooper = Looper.myLooper();
        this.mQueue = mLooper.mQueue;
    }

    // 发送消息，压入队列
    public void sendMessage(Message msg){
        msg.target = this;

        mQueue.enqueueMessage(msg);
    }

    //内部调用，外部实现
    public void handleMessage(Message msg){

    }

    // 转发
    public void dispatchMessage(Message msg){
        handleMessage(msg);
    }
}
