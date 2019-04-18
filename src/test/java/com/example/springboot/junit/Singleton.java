package com.example.springboot.junit;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * Created by vin on 2019/4/18.
 */
@Slf4j
public class Singleton {

    private Singleton() {
    }

    public static Singleton getInstance() {
        return Elvis.INSTANCE.getInstance();
    }

    // Enum singleton - the prefered approach
    public enum Elvis {
        INSTANCE;

        private Singleton singleton;

        //JVM会保证此方法绝对只调用一次
        private Elvis() {
            singleton = new Singleton();
        }

        public Singleton getInstance() {
            return singleton;
        }
    }

    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();//getInstance

        log.info("instance name {}", instance.getClass().getName());
        log.info("instance simple name {}", instance.getClass().getSimpleName());

        // equeals , hashCode parent  Object! Object! Object!
        // so self object need to be override
        //
        Arrays.stream(instance.getClass().getMethods()).forEach(
                method -> log.info("instance method {}", method.getName()));


        log.info("intance canonical name {}", instance.getClass().getCanonicalName());
    }
}
