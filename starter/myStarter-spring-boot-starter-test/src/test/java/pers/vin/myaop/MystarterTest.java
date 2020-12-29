package pers.vin.myaop;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by vin on 2019/3/29.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MystarterTest {

    @Autowired(required = true)
    private TestRestTemplate restTemplate;

    @Test
    public void starterTest() {

        this.restTemplate.getForEntity(
                "/aop", String.class)
                .getStatusCode().is2xxSuccessful();

        this.restTemplate.getForEntity(
                "/aopAspect", String.class)
                .getStatusCode().is2xxSuccessful();


    }

}
