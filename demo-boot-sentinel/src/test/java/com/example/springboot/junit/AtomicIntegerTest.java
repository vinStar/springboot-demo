package com.example.springboot.junit;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by vin on 2019/4/23.
 */
public class AtomicIntegerTest {
	public static AtomicInteger i = new AtomicInteger(0);

	public static int unsafei = 0;

	private static class UnsafeNext extends Thread {

		public void run() {
			unsafei = unsafei + 1;
			System.out.println(unsafei);
		}
	}


	private static class Next extends Thread {

		public void run() {
			int x = i.incrementAndGet();
			System.out.println(x);
		}
	}

	public static void main(String[] args) {

		Thread[] threads = new Thread[10];

		System.out.println("=== unsafe ===");
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(new UnsafeNext());
			threads[i].start();
		}
		try {
			Thread.sleep(100L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("=== safe ===");

		//threads = new Thread[10];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(new Next());
			threads[i].start();
		}

	}

}
