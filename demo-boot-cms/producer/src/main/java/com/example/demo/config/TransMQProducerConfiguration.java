package com.example.demo.config;

import com.example.demo.service.LocalTransactionListenerImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@Slf4j
public class TransMQProducerConfiguration {


	/**
	 * 发送同一类消息的设置为同一个group，保证唯一,默认不需要设置，rocketmq会使用ip@pid(pid代表jvm名字)作为唯一标示
	 */
	@Value("${rocketmq.producer.groupNameTrans}")
	private String groupName;

	@Value("${rocketmq.producer.namesrvAddr}")
	private String namesrvAddr;

	/**
	 * 消息最大大小，默认4M
	 */
	@Value("${rocketmq.producer.maxMessageSize}")
	private Integer maxMessageSize;

	/**
	 * 消息发送超时时间，默认3秒
	 */
	@Value("${rocketmq.producer.sendMsgTimeout}")
	private Integer sendMsgTimeout;
	/**
	 * 消息发送失败重试次数，默认2次
	 */
	@Value("${rocketmq.producer.retryTimesWhenSendFailed}")
	private Integer retryTimesWhenSendFailed;

	@Autowired
	LocalTransactionListenerImpl localTransactionListener;


	@Bean
	public TransactionMQProducer getTransProducer() throws Exception {
		if (StringUtils.isEmpty(this.groupName)) {
			throw new Exception("groupName is blank");
		}
		if (StringUtils.isEmpty(this.namesrvAddr)) {
			throw new Exception("nameServerAddr is blank");
		}
		TransactionMQProducer transProducer = new TransactionMQProducer(this.groupName);
		// 本地事务实现
		transProducer.setTransactionListener(localTransactionListener);
		transProducer.setNamesrvAddr(this.namesrvAddr);
		//如果需要同一个jvm中不同的producer往不同的mq集群发送消息，需要设置不同的instanceName
		//producer.setInstanceName(instanceName);
		if (this.maxMessageSize != null) {
			transProducer.setMaxMessageSize(this.maxMessageSize);
		}
		if (this.sendMsgTimeout != null) {
			transProducer.setSendMsgTimeout(this.sendMsgTimeout);
		}
		//如果发送消息失败，设置重试次数，默认为2次
		if (this.retryTimesWhenSendFailed != null) {
			transProducer.setRetryTimesWhenSendFailed(this.retryTimesWhenSendFailed);
		}

		try {
			transProducer.start();

			log.info(String.format("producer is start ! groupName:[%s],namesrvAddr:[%s]"
					, this.groupName, this.namesrvAddr));
		} catch (MQClientException e) {
			log.error(String.format("producer is error {}"
					, e.getMessage(), e));
			throw new Exception(e);
		}
		return transProducer;
	}
}