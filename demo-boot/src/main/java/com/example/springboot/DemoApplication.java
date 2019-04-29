package com.example.springboot;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class DemoApplication {


	public static void main(String[] args) {

		log.info("start app");
		SpringApplication.run(DemoApplication.class, args);

	}

	@Override
	protected void finalize() throws Throwable {
		System.out.println("垃圾回收机制之前调用。。。");
	}

}
