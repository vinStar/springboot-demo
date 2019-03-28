package com.example.springboot.junit;

//import junit.framework.Assert;

import lombok.Getter;
import lombok.Setter;
import org.junit.Assert;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RBucket;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

/**
 * Created by vin on 2019/2/14.
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RedissonTest {


    @Autowired
    RedissonClient redisson;

    @Autowired(required = true)
    private TestRestTemplate restTemplate;

    @Test
    public void testRedisSetnx() {
        RBucket rBucket = redisson.getBucket("key");
        rBucket.set(1);
        Assert.assertEquals(1, rBucket.get());

        rBucket.getAndDelete();
        Assert.assertEquals(null, rBucket.get());

    }

    @Test
    public void testRedisIncr() throws Exception {
        concurrenceRequest("/redisIncr");

        // 等待子线程完成，或者再用一个countdownlatch 进行 await
        Thread.currentThread().sleep(3000L);

    }


    //    @Name("并发操作，减库存")
    //    @DisplayName("并发操作，减库存")
    @Test
    public void testRedisSafe() throws Exception {
        concurrenceReduce("/redisSafe");
    }

    @Test
    public void testRedisUnsafe() throws Exception {
        concurrenceReduce("/redisUnsafe");
    }

    public void concurrenceReduce(String url) throws InterruptedException {
        //设置一个key，stockNum商品的库存数量为100
        RMap<String, Integer> map = redisson.getMap("myMap");
        map.put("stockNum", 101);
        map.addAndGet("stockNum", -1);
        Assert.assertEquals(100, (int) map.get("stockNum"));

        concurrenceRequest(url);

        // 等待子线程完成，或者再用一个countdownlatch 进行 await
        Thread.currentThread().sleep(2000L);
        Assert.assertEquals(0, (int) map.get("stockNum"));
    }


    @Test
    public void test() {
        this.restTemplate.getForEntity(
                "/{username}?hello=hello", String.class, "sayHi")
                .getStatusCode().is2xxSuccessful();
    }

    @Test
    public void getStars() {
        this.restTemplate.getForEntity(
                "/getStars", String.class)
                .getStatusCode().is2xxSuccessful();
    }


    /**
     * 100 concurrence request
     *
     * @param url - url of request
     */
    public void concurrenceRequest(String url) throws InterruptedException {


        CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < 100; i++) {
            MyWorker myWorker = new MyWorker(this.restTemplate, countDownLatch);
            myWorker.setUrl(url);
            new Thread(myWorker).start();
        }
        countDownLatch.countDown();

    }

    /**
     * child thread implement , send request
     */
    static class MyWorker implements Runnable {
        TestRestTemplate restTemplate;
        CountDownLatch countDownLatch;

        @Getter
        @Setter
        private String url;

        public MyWorker(TestRestTemplate restTemplate, CountDownLatch countDownLatch) {

            this.restTemplate = restTemplate;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {

            try {
                countDownLatch.await();
                doWork();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void doWork() {

            this.restTemplate.getForEntity(
                    getUrl(), String.class)
                    .getStatusCode().is2xxSuccessful();
            log.info("request url");
        }
    }


}

