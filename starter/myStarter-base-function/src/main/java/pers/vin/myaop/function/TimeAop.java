package pers.vin.myaop.function;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.InvocationHandler;

import java.lang.reflect.Method;

/**
 * Created by vin on 2019/3/29.
 */
public class TimeAop implements BeanPostProcessor {


    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        return bean;
    }

    public Object postProcessAfterInitialization(final Object bean, String beanName) throws BeansException {
        Class<?> aClass = bean.getClass();
        RequestTimeAspect annotation = aClass.getAnnotation(RequestTimeAspect.class);
        if (null != annotation) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(aClass);
            enhancer.setCallback(new InvocationHandler() {

                public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                    System.out.println("aop before");
                    try {
                        Object invoke = method.invoke(bean, objects);
                        System.out.println("aop after return");
                        return invoke;
                    } catch (Exception e) {
                        System.out.println("aop after throw");
                        throw e;
                    }
                }
            });
            return enhancer.create();
        }
        return bean;
    }
}
