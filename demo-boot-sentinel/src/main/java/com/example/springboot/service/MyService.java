package com.example.springboot.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

/**
 * Created by vin on 2019/3/19.
 */
@Service
public class MyService {

	//@SentinelResource(value = "doSomehing")
	public void doSomething() {

		System.out.println("do something!!");
	}
}
