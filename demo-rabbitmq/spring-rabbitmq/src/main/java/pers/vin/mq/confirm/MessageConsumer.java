package pers.vin.mq.confirm;


import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MessageConsumer {

	private ConnectionFactory factory;
	private Connection connection;
	private Channel channel;


	public MessageConsumer() {
		factory = new ConnectionFactory();
		factory.setHost("192.168.1.191");
		factory.setUsername("admin");
		factory.setPassword("admin");

		try {
			connection = factory.newConnection();
			channel = connection.createChannel();
		} catch (IOException | TimeoutException e) {

			logger.error("connection failed!", e);

		}
	}

	private Logger logger = LoggerFactory.getLogger(MessageConsumer.class);

	public static void main(String[] args) {
		MessageConsumer messageConsumer = new MessageConsumer();
		messageConsumer.consume("hello");
	}

	public boolean consume(String queueName) {


		try {

			//这里声明queue是为了取消息的时候，queue肯定会存在
			//注意，queueDeclare是幂等的，也就是说，消费者和生产者，不论谁先声明，都只会有一个queue
			channel.queueDeclare(queueName, true, false,
					false, null);

			//在消息确认之前,不接受其他消息
			channel.basicQos(1);

			//这里重写了DefaultConsumer的handleDelivery方法，因为发送的时候对消息进行了getByte()，
			// 在这里要重新组装成String
			Channel finalChannel = channel; //effectively final
			Consumer consumer = new DefaultConsumer(finalChannel) {

				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
						throws IOException {

					String strMessage = new String(body, "UTF-8");
					try {
						logger.info("comsumerTag : " + consumerTag + " ; Received '" + strMessage + "'");

					} finally {
						finalChannel.basicAck(envelope.getDeliveryTag(), false);
					}
				}
			};

			boolean autoAck = false;
			//上面是声明消费者，这里用声明的消费者消费掉队列中的消息
			finalChannel.basicConsume(queueName, autoAck, consumer);

			//这里不能关闭连接，调用了消费方法后，消费者会一直连接着rabbitMQ等待消费

		} catch (IOException e) {
			//失败后记录日志，返回false，代表消费失败
			logger.error("send message faild!", e);
			return false;
		}


		return true;
	}
}
