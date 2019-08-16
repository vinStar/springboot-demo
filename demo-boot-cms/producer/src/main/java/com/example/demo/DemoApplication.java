package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StringUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@Slf4j
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

        log.info("user.name : {} "+        System.getProperty("user.name"));

        InetAddress ip;
        try {
            ip = InetAddress.getLocalHost();
            val hostname = ip.getHostName();

            log.info("ip : {}  hostname : {}",ip.toString(),hostname);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }


    }

}
