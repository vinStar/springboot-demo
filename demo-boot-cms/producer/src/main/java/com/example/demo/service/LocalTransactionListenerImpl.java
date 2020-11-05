package com.example.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Service;

/**
 * Created by vin on 2019-12-23.
 */
@Slf4j
@Service
public class LocalTransactionListenerImpl implements TransactionListener {

	//@return 返回事务状态，COMMIT：提交  ROLLBACK：回滚  UNKNOW：回调
	@Override
	public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {

		log.info("tag : {} message : {}", msg.getTopic(), new String(msg.getBody()));
		// execute local transaction in DB
		// DB success
		if (true) {
			log.info("db transaction is success");
			return LocalTransactionState.COMMIT_MESSAGE;
		}
		//DB fail
		return LocalTransactionState.ROLLBACK_MESSAGE;
	}

	@Override
	public LocalTransactionState checkLocalTransaction(MessageExt msg) {

		log.info("db transaction is unknown , need check trans state");
		//默认一分钟
		//check db state
		if (false)
			return LocalTransactionState.ROLLBACK_MESSAGE;
		else
			return LocalTransactionState.ROLLBACK_MESSAGE;
	}
}
