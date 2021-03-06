package com.example.springboot.completableFuture;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class Shop {

    private String name;

    public Shop(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    Random random = new Random();

    public double getPrice(String product) {
        delay();
        return random.nextDouble() * 100;
    }

    public static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void test()
            throws ExecutionException, InterruptedException {
        List<Shop> shops = Arrays.asList(new Shop("shop1"),
                new Shop("shop2"),
                new Shop("shop3"),
                new Shop("shop4"),
                new Shop("shop5"),
                new Shop("shop6"),
                new Shop("shop7"),
                new Shop("shop8")
        );
//        //  method 1  begin
//        List<String> list = shops.parallelStream().map(shop ->
//                String.format("%s price is %.6f ", shop.getName(), shop.getPice("product")))
//                .collect(Collectors.toList());
//
//        System.out.println(list);
//////  method 1  end
//
        //  method 2================== begin
//        Executor executor = Executors.newCachedThreadPool();

        //recommend
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();

        //Common Thread Pool
        ExecutorService executorService = new ThreadPoolExecutor(
                5,
                8,
                1000L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024),
                namedThreadFactory, new ThreadPoolExecutor.CallerRunsPolicy());
//        executorService.execute(()-> {
//            System.out.println("test");
//        });
        //gracefully shutdown
        //executorService.shutdown();


//
//        CompletableFuture<?>[] priceFuture = shops.parallelStream()
//                .map(shop -> CompletableFuture
//                        .supplyAsync(() -> shop.getPice("product"), executorService)).toArray(size -> new CompletableFuture[size]);
//
//
//        //allOf接收一个数组，当里面的CompletableFuture都完成的时候，就会执行下一个语句
//        CompletableFuture.allOf(priceFuture).thenAccept((obj) -> System.out.println(" all done " + obj));
//        //allOf接收一个数组，当里面的CompletableFuture有一个完成时，就会执行下一个语句
//        //CompletableFuture.anyOf(priceFuture).thenAccept((obj) -> System.out.println("fastest anyOf done " + obj));
//        System.out.println("aaa");

        //  method 2================== end

        //  method 3
        CompletableFuture<Double> aa = CompletableFuture.supplyAsync(() -> new Shop("shop3").getPrice("product"), executorService);
        System.out.println(" supplyAsync price " + aa.get());

        List<Double> listStr = new ArrayList<>();
        CompletableFuture<?>[] priceFuture2 = shops.parallelStream()
                .map(shop -> {
                    return CompletableFuture
                            .supplyAsync(() -> shop.getPrice("product"), executorService)
                            .whenComplete((result, th) -> {
                                System.out.println("price : " + result);
                                listStr.add(result);
                            });
                }).toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(priceFuture2).join();

        System.out.println("result list :" + listStr);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        test();
    }


}
