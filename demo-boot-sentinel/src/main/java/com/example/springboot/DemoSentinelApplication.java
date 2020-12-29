package com.example.springboot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * demo app
 */
@Slf4j
@SpringBootApplication
@EnableAsync
public class DemoSentinelApplication {


    public static void main(String[] args) {

        try {
            log.info("start app");
            SpringApplication.run(DemoSentinelApplication.class, args);
        } catch (Throwable e) {
            e.printStackTrace();
        }


    }

//    @Override
//    protected void finalize() throws Throwable {
//        System.out.println("垃圾回收机制之前调用。。。");
//    }

}
