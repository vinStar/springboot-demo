package com.example.demo.config;


import com.example.demo.task.GoodsTask;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

@Component
public class GoodsListener implements MessageListenerConcurrently {
    private Logger logger = LoggerFactory.getLogger(GoodsListener.class);

    private int nThreads = 5;
    private int maxThreads = 100;
    private int MAX_QUEUQ_SIZE = 2000;
    private ExecutorService executor = new ThreadPoolExecutor(nThreads,
            maxThreads, 0L, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<Runnable>(MAX_QUEUQ_SIZE),
            new ThreadPoolExecutor.CallerRunsPolicy());

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        logger.info(String.format("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs));
        for (MessageExt messageExt : msgs) {



            byte[] body = messageExt.getBody();
            String res = new String(body);
            logger.info("Message Body: {}", res);

            //TODO 商品发布后发送MQ消息，接收到消息后调用商品查询接口，将查询到的商品信息写入ES
            GoodsTask goodsTask = new GoodsTask(res);
            FutureTask<Boolean> futureTask = new FutureTask<>(goodsTask);
            executor.execute(futureTask);
            try {
                Boolean isTrue = futureTask.get();
              //  isTrue = false;
                logger.info(String.format("返回结果 %s", isTrue));
                if (isTrue) {
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return ConsumeConcurrentlyStatus.RECONSUME_LATER;
    }

    /**
     * 把long 转换成 日期 再转换成String类型
     */
    public String transferLongToDate(String dateFormat, Long millSec) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date date = new Date(millSec);
        return sdf.format(date);
    }
}