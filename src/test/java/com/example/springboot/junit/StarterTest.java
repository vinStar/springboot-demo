package com.example.springboot.junit;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by vin on 2019/3/29.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StarterTest {

    @Autowired(required = true)
    private TestRestTemplate restTemplate;


    @Test
    public void starterTest() {

        this.restTemplate.getForEntity(
                "/aop", String.class)
                .getStatusCode().is2xxSuccessful();
    }


}
