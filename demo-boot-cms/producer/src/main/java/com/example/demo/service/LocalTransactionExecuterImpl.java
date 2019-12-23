package com.example.demo.service;

import org.apache.rocketmq.client.producer.LocalTransactionExecuter;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.common.message.Message;

/**
 * Created by vin on 2019-12-23.
 */
public class LocalTransactionExecuterImpl implements LocalTransactionExecuter {
	@Override
	public LocalTransactionState executeLocalTransactionBranch(Message msg, Object arg) {
		System.out.println("执行本地事务msg = " + new String(msg.getBody()));
		System.out.println("执行本地事务arg = " + arg);

		//DB操作 应该带上事务 service -> dao
		//如果数据操作失败  需要回滚    同时返回RocketMQ一条失败消息  意味着消费者无法消费到这条失败的消息
		//如果成功 就要返回一条rocketMQ成功的消息，意味着消费者将读取到这条消息
		//o就是attachment
		String tags = msg.getTags();
		if (tags.equals("transaction2")) {
			System.out.println("===> 本地事务执行失败，进行MQ ROLLBACK");
			return LocalTransactionState.ROLLBACK_MESSAGE;
		}

		System.out.println("===> 本地事务执行成功，发送确认消息");
		// return LocalTransactionState.UNKNOW;
		return LocalTransactionState.COMMIT_MESSAGE;
	}
}
