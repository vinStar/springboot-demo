package com.example.springboot.config;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by vin on 2018/6/21.
 */
@Configuration
public class TestBean2 implements InitializingBean, DisposableBean {


	@Bean
	public IAppName test() {
		return () -> "test bean 2";
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("destroy");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("after properties set");
	}
}
