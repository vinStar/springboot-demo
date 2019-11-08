package com.example.springboot.junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

/**
 * @author lt
 * @date 2019/10/25
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringAsyncInvokeTest {

    @Autowired(required = true)
    private TestRestTemplate restTemplate;


    @Test
    public void runAsyncTest() {

        this.restTemplate.getForEntity(
                "/runAsync", String.class)
                .getStatusCode().is2xxSuccessful();
    }

    @Test
    public void supplyAsyncTest() {

        this.restTemplate.getForEntity(
                "/supplyAsync", String.class)
                .getStatusCode().is2xxSuccessful();
    }

    @Test
    public void runAsyncCfTest() {

        this.restTemplate.getForEntity(
                "/runAsyncCf", String.class)
                .getStatusCode().is2xxSuccessful();
    }

    @Test
    public void supplyAsyncCfTest() {

        this.restTemplate.getForEntity(
                "/supplyAsyncCf", String.class)
                .getStatusCode().is2xxSuccessful();
    }


    /**
     * 生产--消费
     * - thenAccept：同步的
     * - thenAcceptAsync：异步的
     * <p>
     * 接受上一个处理结果，并实现一个Consumer,消费结果
     */
    @Test
    public void test4() {

        //同步的
        CompletableFuture.completedFuture("produce msg")
                .thenAccept(s -> System.out.println("sync consumed msg : " + s));

        //异步的
        //默认线程池
        CompletableFuture.completedFuture("produce msg")
                .thenAcceptAsync(s -> System.out.println("async consumed msg : " + s));
        //指定线程池
        CompletableFuture.completedFuture("produce msg")
                .thenAcceptAsync(s -> System.out.println("async consumed msg : " + s), Executors.newFixedThreadPool(2));
    }



}
