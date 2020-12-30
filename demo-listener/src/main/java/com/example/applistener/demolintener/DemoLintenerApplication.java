package com.example.applistener.demolintener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@SpringBootApplication
public class DemoLintenerApplication {

    @Autowired
    ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(DemoLintenerApplication.class, args);
    }

    @PostConstruct
    void bean() {
        System.out.println("beans -=======-");
        Arrays.asList(applicationContext.getBeanDefinitionNames()).
                stream().forEach(System.out::println);
    }

}
