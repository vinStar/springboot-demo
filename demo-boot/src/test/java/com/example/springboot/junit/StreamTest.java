package com.example.springboot.junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @author lt
 * @date 2019/11/4
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StreamTest {


    @Test
    public void reduceTest() {
        List<Integer> numList = Arrays.asList(1, 2, 3, 4, 5);
        int result = numList.stream().reduce((a, b) -> a + b).get();
        System.out.println(result);
    }

    /**
     * reduce process
     */
    @Test
    public void reduce2Test() {
        List<Integer> numList = Arrays.asList(1, 2, 3, 4, 5);
        int result = numList.stream().reduce((a, b) -> {
            System.out.println("a=" + a + ",b=" + b);
            return a + b;
        }).get();
        System.out.println(result);
    }
}
