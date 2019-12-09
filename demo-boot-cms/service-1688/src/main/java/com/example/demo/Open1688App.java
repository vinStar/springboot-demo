package com.example.demo;

import com.alibaba.tuna.client.api.MessageProcessException;
import com.alibaba.tuna.client.websocket.TunaWebSocketClient;
import com.alibaba.tuna.client.websocket.WebSocketMessage;
import com.alibaba.tuna.client.websocket.WebSocketMessageHandler;
import com.alibaba.tuna.client.websocket.WebSocketMessageType;
import com.spring4all.swagger.EnableSwagger2Doc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@Slf4j
@EnableEurekaClient
@EnableFeignClients
@EnableSwagger2Doc
@SpringBootApplication
public class Open1688App {

    public static void main(String[] args) {
        SpringApplication.run(Open1688App.class, args);

        /*
         * 开放平台1688环境
         */
        String url = "ws://message.1688.com/websocket";
        /**
         * 您的 AppKey
         */
        String appKey = "xxx";
        /**
         * 您的应用秘钥
         */
        String secret = "xxxx";
        /**
         * 您客户端设置的接收线程池大小 默认为虚拟机内核数*40 用户可以自行修改
         */
        int threadNum = 10;

        /**
         * 1. 创建 Client，如果不传入threadNum参数的话，client将使用默认线程数
         */
        TunaWebSocketClient client = new TunaWebSocketClient(appKey, secret, url);
        client.setThreadNum(threadNum);
        /**
         * 2. 创建 消息处理 Handler
         */
        WebSocketMessageHandler tunaMessageHandler = new WebSocketMessageHandler() {
            /**
             * 消费消息。
             * 如果抛异常或返回 false，表明消费失败，如未达重试次数上限，开放平台将会择机重发消息
             */
            @Override
            public boolean onMessage(WebSocketMessage message) throws MessageProcessException {
                boolean success = true;
                /**
                 * 服务端推送的消息分为2种，
                 * 业务数据：SERVER_PUSH
                 * 系统消息：SYSTEM，如 appKey 与 secret 不匹配等，一般可忽略
                 */
                if (WebSocketMessageType.SERVER_PUSH.name().equals(message.getType())) {
                    try {
                        /**
                         * json串
                         */
                        System.out.println("message: " + message);
                    } catch (Exception e) {
                        success = false;
                    }
                }
                return success;
            }
        };
        client.setTunaMessageHandler(tunaMessageHandler);

        /**
         * 3. 启动 Client
         */
        client.connect();

        /**
         * 4. 在应用关闭时，关闭客户端
         */
        // client.shutdown();

    }


}
