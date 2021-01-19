package com.example.springboot.handler.mqmessage;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vin
 */
@Slf4j
@Component
public class MqManager {

    @Autowired
    ApplicationContext applicationContext;

    /**
     * 发送同一类消息的设置为同一个group，保证唯一,默认不需要设置，rocketmq会使用ip@pid(pid代表jvm名字)作为唯一标示
     */
    @Value(value = "${rocketmq.producer.groupName:testGroup}")
    private String groupName;

    @Value("${rocketmq.producer.namesrvAddr:192.168.112.42:9876}")
    private String namesrvAddr;

    /**
     * 消息最大大小，默认4M
     */
//    @Value("${rocketmq.producer.maxMessageSize:}")
//    private Integer maxMessageSize;

    /**
     * 消息发送超时时间，默认3秒
     */
//    @Value("${rocketmq.producer.sendMsgTimeout}")
//    private Integer sendMsgTimeout;
    /**
     * 消息发送失败重试次数，默认2次
     */
    @Value("${rocketmq.producer.retryTimesWhenSendFailed:2}")
    private Integer retryTimesWhenSendFailed;


    public DefaultMQProducer defaultMQProducer;

    public DefaultMQPushConsumer defaultMQPushConsumer;

    public void initMQ(String topicName) {

        defaultMQProducer = new DefaultMQProducer(topicName + "Producer");
        defaultMQProducer.setNamesrvAddr(namesrvAddr);
        try {
            defaultMQProducer.start();
            log.info("producer started topic : {}", topicName);
        } catch (MQClientException e) {
            e.printStackTrace();
        }


        defaultMQPushConsumer = new DefaultMQPushConsumer(topicName + "Consumer");
        defaultMQPushConsumer.setNamesrvAddr(namesrvAddr);
        defaultMQPushConsumer.setConsumeMessageBatchMaxSize(1);
        defaultMQPushConsumer.setConsumeThreadMin(1);
        defaultMQPushConsumer.setConsumeThreadMax(10);
        /**
         * 设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费
         * 如果非第一次启动，那么按照上次消费的位置继续消费
         */
        defaultMQPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        /**
         * 设置消费模型，集群还是广播，默认为集群
         */
        defaultMQPushConsumer.setMessageModel(MessageModel.CLUSTERING);
        defaultMQPushConsumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                for (MessageExt messageExt : list) {
                    byte[] body = messageExt.getBody();
                    String res = new String(body);
                    log.info(" handle topic : {} , message : {}", messageExt.getTopic(), res);
                    Object obj = applicationContext.getBean(res);

                    //对象引用指向 ixxHandler
                    IxxHandler ixxHandler = (IxxHandler) obj;

                    if (obj instanceof IxxHandler) {
                        ixxHandler.handlerMessage();
                        //log.info("name :{}", obj.name);//不可访问

//                        log.info("handle by IxxHandler");
                    }

                    if (obj instanceof AxxHandler) {
                        ((AxxHandler) obj).handlerMessage();
                        log.info("name :{}", ((AxxHandler) obj).name);
                        //log.info("handle by AxxHandler");
                    }

                    if (obj instanceof BxxHandler) {
                        ((BxxHandler) obj).handlerMessage();
//                        log.info("handle by BxxHandler");
                    }

                }

                List<String> list1 = new ArrayList<String>();

                log.info("handle end , return success");
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        try {
            defaultMQPushConsumer.subscribe(topicName, "*");
        } catch (MQClientException e) {
            e.printStackTrace();
        }

        try {
            defaultMQPushConsumer.start();
            log.info("consumer started topic : {}", topicName);
        } catch (MQClientException e) {
            e.printStackTrace();
        }


    }
}
