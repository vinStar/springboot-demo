package com.example.springboot.applicationListener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

@Configuration

@Slf4j
public class MyListener implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        log.info("event {}" + event);
//        ApplicationStartingEvent//启动开始的时候执行的事件
//        ApplicationEnvironmentPreparedEvent//上下文创建之前运行的事件
//        ApplicationContextInitializedEvent//
//        ApplicationPreparedEvent//上下文创建完成，注入的bean还没加载完成
//        ContextRefreshedEvent//上下文刷新
//        ServletWebServerInitializedEvent//web服务器初始化
//        ApplicationStartedEvent//
//        ApplicationReadyEvent//启动成功
//        ApplicationFailedEvent//在启动Spring发生异常时触发
        switch (event.getClass().getSimpleName()) {
            case "ApplicationStartingEvent":
                System.out.println("启动开始的时候执行的事件");
                break;
            case "ApplicationEnvironmentPreparedEvent":
                System.out.println("上下文创建之前运行的事件");
                break;
            case "ApplicationContextInitializedEvent":
                System.out.println("上下文初始化");
                break;
            case "ApplicationPreparedEvent":
                System.out.println("上下文创建完成，注入的bean还没加载完成");
                break;
            case "ContextRefreshedEvent":
                System.out.println("上下文刷新");
                if (event instanceof ContextRefreshedEvent) {
                    Object stu = ((ContextRefreshedEvent) event).getApplicationContext().getBean("haManager");
                    System.out.println(stu);
                }
                break;
            case "ApplicationStartedEvent":
                System.out.println("ApplicationStartedEvent");
                break;
            case "ApplicationReadyEvent":
                System.out.println("启动成功");
                break;
            case "ApplicationFailedEvent":
                break;
        }
    }
}
