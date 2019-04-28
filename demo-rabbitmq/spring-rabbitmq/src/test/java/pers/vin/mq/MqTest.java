package pers.vin.mq;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

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

		val consumeFlag = messageConsumer.consume("hello");

		Assert.assertEquals(true, consumeFlag);

	}


}
