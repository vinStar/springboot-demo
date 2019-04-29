package com.example.springboot.junit;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * Created by vin on 2019/4/16.
 */
@Slf4j
public class ExecutorCompletionServiceManager {


	public static void main(String[] args) {

		ExecutorCompletionService<String> service = new ExecutorCompletionService<String>(
				Executors.newCachedThreadPool());

		// 生产者
		new Thread() {
			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					log.info("index i {}", i);
					final int index = i;
					service.submit(new Callable<String>() {
						@Override
						public String call() throws Exception {
							Thread.sleep(index * 1000L);
							return "task done" + index;
						}
					});
				}
			}
		}.start();


		// 消费者
		new Thread() {
			@Override
			public void run() {
				try {
					int i = 10;
					// -- first
					while (--i >= 0) {
						Future<String> take = service.poll(5, TimeUnit.SECONDS);
						// do some thing........
						log.info(take.get());
					}

				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			}
		}.start();

	}
}
