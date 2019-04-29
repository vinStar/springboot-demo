package com.example.springboot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RAtomicDouble;
import org.redisson.api.RLock;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * Created by vin on 2019/2/14.
 */
@Slf4j
@RestController
@Api(value = "redis", description = "测试redisson")
public class RedissonController {


	@Autowired
	RedissonClient redisson;

	@ApiOperation(value = "redisson", notes = "redisson 分布式锁测试-safe", produces = "application/json")
	@RequestMapping(value = "/redisSafe", method = RequestMethod.GET)
	public String indexSafe() {
		RLock lock = redisson.getLock("redissonLock");

		try {
			log.info("request url");
			// 1. 最常见的使用方法
			//lock.lock();
			// 2. 支持过期解锁功能,10秒钟以后自动解锁, 无需调用unlock方法手动解锁
			//lock.lock(10, TimeUnit.SECONDS);
			// 3. 尝试加锁，最多等待3秒，上锁以后10秒自动解锁
			boolean res = lock.tryLock(3, 10, TimeUnit.SECONDS);
			if (res) { //成功

				// get stock and perform stock reduce 1
				redisson.getMap("myMap").addAndGet("stockNum", -1);

				log.info("now stock is " + redisson.getMap("myMap").get("stockNum"));

			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

		return "ok";
	}

	@ApiOperation(value = "redisson", notes = "redisson 分布式锁测试-unsafe", produces = "application/json")
	@RequestMapping(value = "/redisUnsafe", method = RequestMethod.GET)
	public String indexUnsafe() {
		//RLock lock = redisson.getLock("redissonLock");

		RMap<String, Integer> map = redisson.getMap("myMap");
		// perform stock reduce 1
		map.addAndGet("stockNum", -1);

		log.info("now stock is " + map.get("stockNum"));

		return "ok";
	}

	@ApiOperation(value = "redisson", notes = "redisson 分布式锁测试-incr", produces = "application/json")
	@RequestMapping(value = "/redisIncr", method = RequestMethod.GET)
	public String indexIncr() {


		RAtomicDouble rAtomicDouble = redisson.getAtomicDouble("myIncr");
		// increment 1
		log.info("now num is " + rAtomicDouble.incrementAndGet());

		return "ok";
	}


	//RAtomicLong.incrementAndGet(),


}
