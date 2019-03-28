package com.example.springboot.junit;

import com.example.springboot.service.MyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by vin on 2019/3/19.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyServiceTest {
    @Autowired
    private MyService myService;

    @Test
    public void contextLoads() {
        myService.doSomething();
    }

}
