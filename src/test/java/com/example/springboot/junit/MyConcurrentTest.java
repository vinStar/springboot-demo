package com.example.springboot.junit;

import com.example.springboot.utils.Concurrent;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by vin on 2019/3/28.
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MyConcurrentTest {


    @Test
    public void multiThreadConcurrent() {
        String url = "http://192.168.1.191:8071/qa/question?qType=1&index=1";

        try {
            // concurrent so requestNum = concurrentThreadNum
            Concurrent.requestMulti(20, 20, url);
            // wait child thread end
            Thread.currentThread().sleep(2000l);
            log.info("main thread end.");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void multiThreadConcurrentUntilCompleted() {
        String url = "http://192.168.1.191:8071/qa/question?qType=1&index=1";

        try {
            // concurrent so requestNum = concurrentThreadNum
            Concurrent.requestMultiUntilCompleted(20, 20, url);
            //  without below is still work
            // Thread.currentThread().sleep(2000l);
            log.info("main thread end.");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * 1. specified concurrent user(thread) num
     * 2. specified total num
     */
    @Test
    public void controlConcurrentThreadNum() {
        String url = "http://192.168.1.191:8071/qa/question?qType=1&index=1";

        try {
            // reques total 1000 thread num 100 , passed - 1500ms
            // reques total 1000 thread num 20  , passed - 1700ms
            Concurrent.request(1000, 100, url);

            log.info("main thread end.");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
