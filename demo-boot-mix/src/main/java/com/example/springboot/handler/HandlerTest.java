package com.example.springboot.handler;

import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public class HandlerTest {


    public void test() {
        //轮询器初始化
        Looper.prepare();

        // 主线程当中
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                System.out.println(Thread.currentThread().getName() + ",receive:" + msg.toString());
            }
        };

        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    while (true) {
                        Message msg = new Message();
                        msg.what = 1;
                        synchronized (UUID.class) {
                            log.info("send message {} ");
                            msg.obj = Thread.currentThread().getName() + ",send message" + UUID.randomUUID().toString();
                        }
                        System.out.println(msg);
                        handler.sendMessage(msg);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        }

        //开启轮询
        Looper.loop();
    }


}
