package pers.vin.mq;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.MessageProperties;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


@Slf4j
public class MessageSender {


	//声明一个队列名字
	private final static String QUEUE_NAME = "hello";


	public boolean sendMessage(String message) {
		//new一个RabbitMQ的连接工厂
		ConnectionFactory factory = new ConnectionFactory();
		//设置需要连接的RabbitMQ地址，这里指向本机
		factory.setHost("192.168.1.191");

//		factory.setPort(5672);
		factory.setUsername("admin");
		factory.setPassword("admin");
		Connection connection = null;
		Channel channel = null;
		try {
			//尝试获取一个连接
			connection = factory.newConnection();

			//尝试创建一个channel
			channel = connection.createChannel();

			//这里的参数在后面详解
			channel.queueDeclare(QUEUE_NAME, true, false, false, null);
			//注意这里调用了getBytes()，发送的其实是byte数组，接收方收到消息后，需要重新组装成String
			channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes("UTF-8"));



			log.info("Sent '" + message + "'");
			//关闭channel和连接
			channel.close();
			connection.close();
		} catch (IOException | TimeoutException e) {
			//失败后记录日志，返回false，代表发送失败
			log.error("send message faild!", e);
			return false;
		}
		return true;
	}


	public static void main(String[] args) {

		MessageSender messageSender = new MessageSender();
		messageSender.sendMessage("bitchin' badass");

	}
}
