package pers.vin.mq.exchange;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by vin on 2019/4/30.
 */

@Slf4j
@RunWith(SpringRunner.class)
public class ExchanageTest {

	MessageSender messageSender = new MessageSender();

	MessageConsumer messageConsumer = new MessageConsumer();


	@Test
	public void sendMessage() {

		val sendFlag = messageSender.sendMessage(" you are a badass!");
		Assert.assertEquals(true, sendFlag);

		ExecutorService executorService = Executors.newFixedThreadPool(200);
		CountDownLatch endSignal = new CountDownLatch(500);

		for (int i = 0; i < 500; i++) {
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
		} finally {
			messageSender.closeAll();

		}
		executorService.shutdown();


		val consumeFlag = messageConsumer.consume("queue_logs_record");

		try {
			Thread.sleep(5000l);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(true, consumeFlag);

	}

}
