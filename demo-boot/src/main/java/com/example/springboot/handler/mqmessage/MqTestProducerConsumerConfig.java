package com.example.springboot.handler.mqmessage;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.acl.common.AclClientRPCHook;
import org.apache.rocketmq.acl.common.SessionCredentials;
import org.apache.rocketmq.client.AccessChannel;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.remoting.RPCHook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author vin
 * 静态方法getAclRPCHook为导入阿里云的认证密钥
 * <p>
 * 生产者初始化DefaultMQProducer时判断一下当前运行环境,如果为生产环境,
 * 则构造连接阿里云MQ的producer, 否则用之前构造方法
 * <p>
 * <p>
 * 生产者组名称有固定格式,需要换一下 此为必须项,
 * <p>
 * 1. 以 “GID_” 或者 “GID-” 开头，只能包含字母、数字、短横线（-）和下划线（_）；
 * 2. 长度限制在 7-64 字符之间；
 * 例如 订单服务使用的生产者组名称: GID-order-service-producer
 * <p>
 * <p>
 * 改完组名称同运维同步一下,运维在阿里云mq后台创建组名称.
 */
@Slf4j
//@Configuration
public class MqTestProducerConsumerConfig {

    /**
     * 发送同一类消息的设置为同一个group，保证唯一,默认不需要设置，rocketmq会使用ip@pid(pid代表jvm名字)作为唯一标示
     */
    @Value("${rocketmq.producer.groupName}")
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

    @Value("${spring.profiles.active}")
    private String active;

//    // 设置为您在阿里云 RocketMQ 控制台上创建的 GID, 以及替换为您的 AccessKeyId 和 AccessKeySecret
//    private static RPCHook getAclRPCHook() {
//        return new AclClientRPCHook(
//                new SessionCredentials("xx", "xx"));
//    }

    @Bean
    public DefaultMQProducer getRocketMQProducer() {
//        if (StringUtils.isEmpty(this.groupName)) {
//            throw new RocketMQException("groupName is blank");
//        }
//        if (StringUtils.isEmpty(this.namesrvAddr)) {
//            throw new RocketMQException("nameServerAddr is blank");
//        }
        DefaultMQProducer producer;
        if ("prod".equals(active)) {
            // 生产环境使用阿里云mq
//            producer = new DefaultMQProducer(this.groupName, getAclRPCHook(), true, null);
//            // 设置使用接入方式为阿里云，在使用云上消息轨迹的时候，需要设置此项，如果不开启消息轨迹功能，则运行不设置此项.
//            producer.setAccessChannel(AccessChannel.CLOUD);

        } else {
            producer = new DefaultMQProducer(this.groupName);
        }
        producer = new DefaultMQProducer(this.groupName);
        // producer = new DefaultMQProducer(this.groupName);
        producer.setNamesrvAddr(this.namesrvAddr);
        //如果需要同一个jvm中不同的producer往不同的mq集群发送消息，需要设置不同的instanceName
        //producer.setInstanceName(instanceName);
        if (this.maxMessageSize != null) {
            producer.setMaxMessageSize(this.maxMessageSize);
        }
        if (this.sendMsgTimeout != null) {
            producer.setSendMsgTimeout(this.sendMsgTimeout);
        }
        //如果发送消息失败，设置重试次数，默认为2次
        if (this.retryTimesWhenSendFailed != null) {
            producer.setRetryTimesWhenSendFailed(this.retryTimesWhenSendFailed);
        }

        try {
            producer.start();

            log.info(String.format("producer is start ! groupName:[%s],namesrvAddr:[%s]"
                    , this.groupName, this.namesrvAddr));
        } catch (MQClientException e) {
            log.error(String.format("producer is error {}"
                    , e.getMessage(), e));
            //throw new RocketMQException(e);
        }
        return producer;
    }
}
