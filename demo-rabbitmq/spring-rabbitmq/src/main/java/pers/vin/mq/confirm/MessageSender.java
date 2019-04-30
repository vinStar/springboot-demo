package pers.vin.mq.confirm;

import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


/**
 * 1. 消息持久化，需要exchange ，queue ，message 同时持久化
 * 2. 没有队列订阅的 exchange 接收到消息后直接遗弃
 */

@Slf4j
public class MessageSender {

	// 封闭原则
	private ConnectionFactory factory;
	private Connection connection;
	private Channel channel;

	// 构造方法初始一次，共用
	public MessageSender() {

		//new一个RabbitMQ的连接工厂
		factory = new ConnectionFactory();
		//设置需要连接的RabbitMQ地址
		factory.setHost("192.168.1.191");
		//factory.setPort(5672);
		factory.setUsername("admin");
		factory.setPassword("admin");
		factory.setConnectionTimeout(3000);

		//尝试获取一个连接
		//尝试创建一个channel
		try {
			connection = factory.newConnection();
			channel = connection.createChannel();
		} catch (IOException | TimeoutException e) {
			e.printStackTrace();
		}


	}

	public boolean sendMessage(String message) {


		//消息持久化，需要exchange ，queue ，message 同时持久化
		try {
			//声明一个exchange，命名为logs，类型为fanout
			channel.exchangeDeclare("logs", BuiltinExchangeType.FANOUT, true);
			channel.queueDeclare("confirm_logs_record", true,
					false, false, null);

			//不绑定一个队列的话，消息就回被遗弃
			channel.queueBind("confirm_logs_record", "logs", "");

			channel.confirmSelect();

			//exchange是logs，表示发送到此Exchange上
			//fanout类型的exchange，忽略routingKey，所以第二个参数为空
			channel.basicPublish("logs", "", MessageProperties.PERSISTENT_TEXT_PLAIN,
					message.getBytes("UTF-8"));

			if (channel.waitForConfirms()) {
				log.info("消息发送成功 {}", message);
			}


		} catch (Exception e) {
			//失败后记录日志，返回false，代表发送失败
			log.error("send message failed!", e);
			return false;
		}
		return true;
	}

	public boolean sendMessageAsyncConfirm(String message) {


		//消息持久化，需要exchange ，queue ，message 同时持久化
		try {
			//声明一个exchange，命名为logs，类型为fanout
			channel.exchangeDeclare("logs", BuiltinExchangeType.FANOUT, true);
			channel.queueDeclare("confirm_logs_record", true,
					false, false, null);

			//不绑定一个队列的话，消息就回被遗弃
			channel.queueBind("confirm_logs_record", "logs", "");

			channel.confirmSelect();

			//exchange是logs，表示发送到此Exchange上
			//fanout类型的exchange，忽略routingKey，所以第二个参数为空
			channel.basicPublish("logs", "", MessageProperties.PERSISTENT_TEXT_PLAIN,
					message.getBytes("UTF-8"));

			//异步监听确认和未确认的消息
			channel.addConfirmListener(new ConfirmListener() {
				@Override
				public void handleNack(long deliveryTag, boolean multiple) throws IOException {
					log.info("未确认消息，标识：" + deliveryTag);
				}

				@Override
				public void handleAck(long deliveryTag, boolean multiple) throws IOException {
					log.info(String.format("已确认消息，标识：%d，多个消息：%b", deliveryTag, multiple));
				}
			});


		} catch (Exception e) {
			//失败后记录日志，返回false，代表发送失败
			log.error("send message failed!", e);
			return false;
		}
		return true;
	}

	public boolean closeAll() {
		//关闭channel和连接
		try {
			channel.close();
			connection.close();
		} catch (IOException | TimeoutException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}


	public static void main(String[] args) {

		MessageSender messageSender = new MessageSender();
		messageSender.sendMessage("bitchin' badass");

	}
}
