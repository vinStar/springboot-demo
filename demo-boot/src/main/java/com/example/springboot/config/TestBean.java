package com.example.springboot.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created by vin on 2018/6/21.
 */

@Configuration
public class TestBean {


	@Bean
	public IAppName test() {
//        return  new IAppName() {
//            @Override
//            public String getName() {
//                return "test bean";
//            }
//        }

		return () -> "test bean";
	}


	@PostConstruct
	public void constructPrint() {
		System.out.println("PostConstruct :: construct");
	}

	@PreDestroy
	public void preDestroy() {
		System.out.println("PreDestroy :: preDestroy");
	}
}
