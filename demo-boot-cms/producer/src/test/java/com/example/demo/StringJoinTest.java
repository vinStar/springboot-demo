package com.example.demo;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;//必须是static

/**
 * @author lt
 * @date 2019/7/26
 */
@SpringBootTest
public class StringJoinTest {

    @Test
    public void stringTest() {

        String[] arrayString = new String[]{"t1", "t2"};
        String strJoin = String.join(",", arrayString);

        System.out.println(" String.join(\",\", arrayString)  : " + strJoin);

        assertNotNull(strJoin);


        List<String> listString = Arrays.asList("l1", "l2");

        String strJoin2 = listString.stream().collect(Collectors.joining(", "));

        System.out.println(" listString.stream().collect(Collectors.joining(\", \")) ：" + strJoin2);


    }
}
