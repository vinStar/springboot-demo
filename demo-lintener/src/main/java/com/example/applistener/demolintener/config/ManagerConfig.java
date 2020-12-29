package com.example.applistener.demolintener.config;

import com.example.applistener.demolintener.manager.Manager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ManagerConfig {
    @Bean
    public Manager haManager(@Value("${tbex.ha.sleepSecondsBeforeStartup}") long sleepSecondsBeforeStartup,
                             @Value("${tbex.ha.sleepSecondsBeforeExit}") long sleepSecondsBeforeExit,
                             @Value("${tbex.ha.locks}") String locks) {
        log.info("new manager");
        return new Manager(sleepSecondsBeforeStartup, sleepSecondsBeforeExit, locks);
    }


//    @Bean
//    public Manager haManager(@Value("${tbex.ha.sleepSecondsBeforeStartup}") long sleepSecondsBeforeStartup,
//                             @Value("${tbex.ha.sleepSecondsBeforeExit}") long sleepSecondsBeforeExit,
//                             @Value("${tbex.ha.locks}") String locks) {
//        log.info("new manager");
//        return new Manager(sleepSecondsBeforeStartup, sleepSecondsBeforeExit, locks);
//    }

}
