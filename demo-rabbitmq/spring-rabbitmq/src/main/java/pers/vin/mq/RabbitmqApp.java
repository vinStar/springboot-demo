package pers.vin.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by vin on 2019/4/28.
 */
@SpringBootApplication
@Slf4j
public class RabbitmqApp {

	public static void main(String[] args) {

		log.info("start app");
		SpringApplication.run(RabbitmqApp.class, args);


	}


}
