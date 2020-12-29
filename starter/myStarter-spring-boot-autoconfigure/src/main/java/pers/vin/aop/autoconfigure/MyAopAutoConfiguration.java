package pers.vin.aop.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pers.vin.myaop.function.ExampleAspect;
import pers.vin.myaop.function.TimeAop;

import java.util.concurrent.TimeUnit;

/**
 * Created by vin on 2019/3/29.
 */
@Configuration
@EnableConfigurationProperties(MyAopProperties.class)//使绑定的外部配置生效
public class MyAopAutoConfiguration {

    //在没有这个bean时才注入，比如，你想要对AopCreater里的行为做修改，
    // 你可以自己注入一个AopCreater，这样这个bean就不会被注入
//    @Bean
//    @ConditionalOnProperty(name = "my.aop.enable", havingValue = "false")
//    ExampleAspect aopCreater() {
//        ExampleAspect aopCreater = new ExampleAspect();
//        return aopCreater;
//    }

    @Bean
    @ConditionalOnClass({Enhancer.class})//当依赖中存在Enhancer时，这个配置类才生效
    @ConditionalOnProperty(name = "my.aop.enable", havingValue = "true")
    @ConditionalOnMissingBean
    TimeAop aopLogCreater() {
        TimeAop aopLogCreater = new TimeAop();
        return aopLogCreater;
    }

}
