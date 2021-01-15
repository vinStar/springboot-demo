package com.example.springboot;

import com.example.springboot.handler.HandlerTest;
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
public class DemoMixApplication {


    public static void main(String[] args) {

        try {

//            SpringApplication app = new SpringApplication(DemoApplication.class);
//            app.addListeners(new MyListener());
//            app.addListeners(new SpringStartListener());
//            app.run(args);


            log.info("start app");
            SpringApplication.run(DemoMixApplication.class, args);

            //test handler
            HandlerTest handlerTest = new HandlerTest();
            handlerTest.test();
        } catch (Throwable e) {
            e.printStackTrace();
        }


    }

//    @Override
//    protected void finalize() throws Throwable {
//        System.out.println("垃圾回收机制之前调用。。。");
//    }

}
