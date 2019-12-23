package com.example.demo.controller;


import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class MqController {

	/**
	 * 使用RocketMq的生产者
	 */
	@Autowired()
	@Qualifier("getRocketMQProducer")
	private DefaultMQProducer defaultMQProducer;

	@Value("${rocketmq.producer.testTopic}")
	String testTopic;

	@RequestMapping("producer")
	public void encrypt(String input) {

		Long msg = 2019070200000031L;
		log.info("开始发送消息：" + msg);
		Message sendMsg = new Message(testTopic, "DemoTag", msg.toString().getBytes());

		//默认3秒超时
		SendResult sendResult = null;
		try {
			sendResult = defaultMQProducer.send(sendMsg);
			sendResult.getSendStatus();

		} catch (MQClientException e) {
			e.printStackTrace();
		} catch (RemotingException e) {
			e.printStackTrace();
		} catch (MQBrokerException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		log.info("消息发送响应信息：" + sendResult.toString());

		List<String> stringList = new ArrayList<>();
	}


}
