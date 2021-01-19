package com.example.springboot.utils;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.*;
import java.util.stream.Stream;

/**
 * Created by vin on 2019/3/8.
 * <p>
 * 1. multi thread concurrent
 * 1.1 countdownlatch  await countdown
 * 1.2 semaphore  acquire release
 * used to restrict the number of threads
 * than can access some (physical or logical) resource
 */

@Slf4j
public class Concurrent {


	public static final int requestTotal = 100;
	public static final int concurrentThreadNum = 20;

	public static void main(String[] args) throws InterruptedException {
		request(requestTotal, concurrentThreadNum, "http://10.0.1.191:8071/qa/question?qType=1&index=1");
		//requestMulti(requestTotal, concurrentThreadNum, "http://192.168.1.191:8071/qa/question?qType=1&index=1");
	}

	/**
	 * 1. specified concurrent user(thread) num
	 * 2. specified total num
	 */
	public static void request(int requestTotal, int concurrentThreadNum, String url) throws InterruptedException {

		log.info(url);

		ExecutorService executorService = Executors.newCachedThreadPool();
		CountDownLatch countDownLatch = new CountDownLatch(requestTotal);
		Semaphore semaphore = new Semaphore(concurrentThreadNum);
		for (int i = 0; i < requestTotal; i++) {
			final int num = i;
			executorService.execute(() -> {
				try {
					// allow 20 concurrent thread(user)
					semaphore.acquire();
					log.info("availablePermits {} acquire id:{}.", semaphore.availablePermits(), num);
					String result = testRequestUri(url);
					semaphore.release();
					log.info("availablePermits {} release id:{}.", semaphore.availablePermits(), num);
				} catch (InterruptedException e) {
					log.error("exception", e);
				}
				countDownLatch.countDown();
			});
		}
		countDownLatch.await();
		executorService.shutdown();
		log.info("请求完成");

	}

	public static void requestMulti(int requestTotal, int concurrentThreadNum, String url) throws InterruptedException {

		ExecutorService executorService = Executors.newFixedThreadPool(concurrentThreadNum);
		CountDownLatch countDownLatch = new CountDownLatch(1);

		for (int i = 0; i < requestTotal; i++) {
			final int num = i;
			// Runnable run;
			executorService.execute(() -> {
				try {
					// wait for count down
					countDownLatch.await();
					log.info("request num:{}.", num);
					testRequestUri(url);

				} catch (InterruptedException e) {
					log.error("exception", e);
				}
			});
		}
		countDownLatch.countDown();
		executorService.shutdown();
		log.info("请求完成");

	}

	public static void requestMultiUntilCompleted(int requestTotal, int concurrentThreadNum, String url) throws InterruptedException {

		ExecutorService executorService = Executors.newFixedThreadPool(concurrentThreadNum);
		CountDownLatch startSignal = new CountDownLatch(1);
		CountDownLatch endSignal = new CountDownLatch(requestTotal);

		for (int i = 0; i < requestTotal; i++) {
			final int num = i;
			// Runnable run;
			executorService.execute(() -> {
				try {
					// wait for count down
					startSignal.await();
					log.info("request num:{}.", num);
					testRequestUri(url);
					endSignal.countDown();

				} catch (InterruptedException e) {
					log.error("exception", e);
				}
			});
		}
		startSignal.countDown();
		endSignal.await();
		executorService.shutdown();
		log.info("请求完成");

	}

	private static String testRequestUri(String url) {

		OkHttpClient client = new OkHttpClient();

		//log.info(Integer.toString(client.connectTimeoutMillis()));
		OkHttpClient eagerClient = client.newBuilder()
				.connectTimeout(5, TimeUnit.SECONDS)
				.readTimeout(5, TimeUnit.SECONDS)
				.writeTimeout(5, TimeUnit.SECONDS)
				.build();

		Request request = null;
		String sResult = null;
		try {
			request = new Request.Builder()
					.url(url)
					.addHeader("User-Agent", "mozilla")
					.build();

			Response response = eagerClient.newCall(request).execute();
			sResult = response.body().string();
			log.info("result:{}.", sResult);
			// System.out.println(str);
		} catch (IOException ioe) {
			log.error(ioe.getMessage());
		} finally {
			request = null;
			client = null;
		}

		return sResult;

	}
}
