package com.example.springboot.controller;

import com.example.springboot.component.TaskFutureService;
import com.example.springboot.component.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * @author lt
 * @date 2019/10/25
 */
@RestController
@Slf4j
public class AsyncInvokeController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskFutureService taskFutureService;

    @GetMapping("/runAsync")
    public String runAsync() {
        try {
            Long l1 = System.currentTimeMillis();
            taskService.doTaskOne();
            taskService.doTaskTwo();
            taskService.doTaskThree();
            Long l2 = System.currentTimeMillis();

            log.info("我是主线程");
            log.info("begin--主线程休眠 1500 ms ，每个异步子任务 休眠 1000 ms");
            Thread.sleep(1500L);
            log.info("end--主线程休眠 1500 ms ");
        } catch (Exception e) {
            log.error("error executing task for {}", e.getMessage());
        }
        return "ok";
    }

    /**
     * 文档：如何在 Spring 异步调用中传递上下文.n...
     * 链接：http://note.youdao.com/noteshare?id=85ed2754c6041851a84355e77678ffb4&sub=8B4C0ADEB2944D6FBA4E9360AA5CBB53
     * @return
     */
    @GetMapping("/supplyAsync")
    public String supplyAsync() {
        try {
            Long l1 = System.currentTimeMillis();
            Future<String> r1 = taskFutureService.doTaskOne();
            Future<String> r2 = taskFutureService.doTaskTwo();
            Future<String> r3 = taskFutureService.doTaskThree();
            Long l2 = System.currentTimeMillis();

            log.info("我是主线程");

            // wait for the result method 1
//            while (true) {
//                if (r1.isDone() && r2.isDone() && r3.isDone()) {
//                    log.info("execute all tasks");
//                    break;
//                }
//                Thread.sleep(200);
//            }

            // wait for the result method 2
            log.info("begin--获取子线程结果");
            String s1 = r1.get(2000, TimeUnit.MILLISECONDS);
            String s2 = r2.get(2, TimeUnit.SECONDS);
            String s3 = r3.get(2, TimeUnit.SECONDS);

            log.info("任务 1 {} ，任务 2 {} ，任务 3 {}", s1, s2, s3);

        } catch (Exception e) {
            log.error("error executing task for {}", e.getMessage());
        }
        return "ok";
    }

    @GetMapping("/runAsyncCf")
    public String runAsyncCf() {
        try {
            Long l1 = System.currentTimeMillis();
            CompletableFuture.runAsync(new MyThreadt333());
            CompletableFuture.runAsync(() -> {
                try {
                    taskService.doTaskOne();
                } catch (Exception e) {
                }
            });

        } catch (Exception e) {
            log.error("error executing task for {}", e.getMessage());
        }
        return "ok";
    }

    /**
     * 文档：Java8新的异步编程方式 CompletableFut...
     * 链接：http://note.youdao.com/noteshare?id=9ea3b6696373c66225e9fbbebe56ee4f&sub=E66616995C204723A7A5C5B03C14114F
     * @return
     */
    @GetMapping("/supplyAsyncCf")
    public String supplyAsyncCf() {
        try {
            Long l1 = System.currentTimeMillis();
            CompletableFuture<String> future0 = CompletableFuture.supplyAsync(new MyThreadt444("2"));
            CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
                log.info(" 开始任务 future1");
                String s1 = taskService.doTaskFour();
                return s1;
            });

            CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
                log.info("开始任务 future2");
                String s1 = taskService.doTaskFive();
                return s1;
            });

            log.info("future0 get : {}", future0.get(2, TimeUnit.SECONDS));
            log.info("future1 get : {}", future1.get(2, TimeUnit.SECONDS));
            log.info("future2 get : {}", future2.get(2, TimeUnit.SECONDS));
            Long l2 = System.currentTimeMillis();
            log.info("耗时 : {}", l2 - l1);
        } catch (Exception e) {
            log.error("error executing task for {}", e.getMessage());
        }
        return "ok";
    }

}

class MyThreadt333 implements Runnable {


    @Override
    public void run() {
        System.out.println("Runnable");
    }
}

@Slf4j
class MyThreadt444 implements Supplier<String> {


    // 要运行的mingling
    private String commandstr;

    public MyThreadt444(String commandstr) {
        log.info("开始任务 future0");
        this.commandstr = commandstr;
    }

    @Override
    public String get() {
        int sum = 0;
        for (int i = 0; i < 4; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            sum += i;
            System.out.println("Mythread444: " + i);
        }
        return String.valueOf(sum + 400000);
    }
}


