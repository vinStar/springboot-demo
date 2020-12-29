package com.example.applistener.demolintener.config;


import com.example.applistener.demolintener.applicationListener.MyListener;
import com.example.applistener.demolintener.applicationListener.SpringStartListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ListenerConfig {


    @Bean
    public SpringStartListener springStartListener() {
        return new SpringStartListener();
    }

    @Bean
    public MyListener myListener() {
        return new MyListener();
    }
}
