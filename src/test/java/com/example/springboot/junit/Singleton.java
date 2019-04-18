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

        int[] nums = {1, 20, 63, 58, 185, 60, 59, 20};
        Arrays.stream(nums).sorted().forEach(System.out::println);


        /**
         * 将下列集合中的所有数字乘以10,并得到新集合[1, 20, 63, 58, 185, 60, 59, 20]
         */
        Integer[] num1 = {1, 20, 63, 58, 185, 60, 59, 20};
        Integer[] num2 = Arrays.stream(num1).map(n -> n * 10).toArray(Integer[]::new);
        Arrays.stream(num2).forEach(System.out::println);


        /**
         * 取出下列集合中的第3到7个值，加上5，得到新的集合[1, 20, 63, 58, 185, 60, 59, 20]
         */
        Integer[] num3 = {1, 20, 63, 58, 185, 60, 59, 20};
        Integer[] num4 = Arrays.stream(num3).skip(2).limit(5).map(x -> x + 5).toArray(Integer[]::new);
        Arrays.stream(num4).forEach(System.out::println);


        log.info("intance canonical name {}", instance.getClass().getCanonicalName());
    }
}
