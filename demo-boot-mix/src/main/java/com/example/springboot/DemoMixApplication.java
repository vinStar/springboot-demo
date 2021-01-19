package com.example.springboot;

import com.example.springboot.handler.HandlerTest;
import com.example.springboot.handler.mqmessage.MqManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.annotation.PostConstruct;

/**
 * demo app
 */
@Slf4j
@SpringBootApplication
@EnableAsync
public class DemoMixApplication {

    @Autowired
    MqManager mqManager;


    public static void main(String[] args) {

        try {

//            SpringApplication app = new SpringApplication(DemoApplication.class);
//            app.addListeners(new MyListener());
//            app.addListeners(new SpringStartListener());
//            app.run(args);


            log.info("start app");
            SpringApplication.run(DemoMixApplication.class, args);


        } catch (Throwable e) {
            e.printStackTrace();
        }


    }

    @PostConstruct
    void testHandler() {
        //test handler
//        HandlerTest handlerTest = new HandlerTest();
//        handlerTest.test();
    }


    @PostConstruct
    void testHandlerMessage() {
        mqManager.initMQ("SELF_TEST_TOPIC");
        mqManager.initMQ("BenchmarkTest");
    }

//    @Override
//    protected void finalize() throws Throwable {
//        System.out.println("垃圾回收机制之前调用。。。");
//    }

}
