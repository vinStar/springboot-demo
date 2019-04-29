package pers.vin.mq;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by vin on 2019/4/28.
 */
@Slf4j
@RunWith(SpringRunner.class)
public class MqTest {

	MessageSender messageSender = new MessageSender();

	MessageConsumer messageConsumer = new MessageConsumer();

	@Test
	public void sendMessage() {

		val sendFlag = messageSender.sendMessage("bitchin' badass");
		Assert.assertEquals(true, sendFlag);

		ExecutorService executorService = Executors.newFixedThreadPool(200);
		CountDownLatch endSignal = new CountDownLatch(10000);

		for (int i = 0; i < 10000; i++) {
			final int num = i;
			// Runnable run;
			executorService.execute(() -> {
				try {
					messageSender.sendMessage("bitchin' badass" + num);
					endSignal.countDown();
				} catch (Exception e) {
					log.error("exception", e);
				}
			});
		}

		try {
			endSignal.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		executorService.shutdown();

//
//		 val consumeFlag = messageConsumer.consume("hello");
//
//		 Assert.assertEquals(true, consumeFlag);

	}


}
