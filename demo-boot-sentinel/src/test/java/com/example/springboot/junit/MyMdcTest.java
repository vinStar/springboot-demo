package com.example.springboot.junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by vin on 2019/3/22.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MyMdcTest {

	@Autowired(required = true)
	private TestRestTemplate restTemplate;


	@Test
	public void testMdc() {

		this.restTemplate.getForEntity(
				"/mdc", String.class)
				.getStatusCode().is2xxSuccessful();

	}

}
