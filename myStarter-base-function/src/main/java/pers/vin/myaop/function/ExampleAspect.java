package pers.vin.myaop.function;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Created by vin on 2019/3/29.
 */

@Aspect
@Configuration
@ConditionalOnProperty(name = "my.aop.enable", havingValue = "false")
public class ExampleAspect {

//    this doesn’t change the behavior of anything yet,
//    @Around("@annotation(LogExecutionTime)")
//    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
//        return joinPoint.proceed();
//    }

    @Around("@annotation(LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - start;

        System.out.println(joinPoint.getSignature() + " executed in " + executionTime + "ms");
        return proceed;
    }
}
