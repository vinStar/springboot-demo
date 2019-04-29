package com.example.springboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.MDC;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Created by vin on 2019/3/22.
 */

@Slf4j
@RestController
public class MdcController {

	@RequestMapping("mdc")
	public void testMdc() {

		MDC.put("Transaction-ID", UUID.randomUUID().toString());
		log.info("mdc Hello world");
		MDC.clear();
		log.info(" Hello world");
	}

}
