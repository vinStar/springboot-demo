package com.example.demo;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableEurekaClient
@EnableFeignClients
@EnableSwagger2Doc
@SpringBootApplication
public class ServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(ServiceApp.class, args);
    }

}
