package com.example.springboot.CompletableFuture;

import javax.annotation.processing.Completion;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Shop {

    private String name;

    public Shop(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    Random random = new Random();

    public double getPice(String product) {
        delay();
        return random.nextDouble() * 100;
    }

    public static void delay() {
        try {
            Thread.sleep(1000l);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Shop> shops = Arrays.asList(new Shop("shop1"),
                new Shop("shop2"),
                new Shop("shop3"),
                new Shop("shop4"),
                new Shop("shop5"),
                new Shop("shop6"),
                new Shop("shop7"),
                new Shop("shop8")
        );
        //  method 1
        List<String> list = shops.parallelStream().map(shop ->
                String.format("%s price is %.6f ", shop.getName(), shop.getPice("product")))
                .collect(Collectors.toList());

        System.out.println(list);

//
        Executor executor = Executors.newCachedThreadPool();
        //  method 2==================
//
//        CompletableFuture<?>[] priceFuture = shops.parallelStream()
//                .map(shop -> CompletableFuture
//                        .supplyAsync(() -> shop.getPice("product"), executor)).toArray(size -> new CompletableFuture[size]);
//
//
//        //allOf接收一个数组，当里面的CompletableFuture都完成的时候，就会执行下一个语句
//        CompletableFuture.allOf(priceFuture).thenAccept((obj) -> System.out.println(" all done " + obj));
//        //allOf接收一个数组，当里面的CompletableFuture有一个完成时，就会执行下一个语句
//        //CompletableFuture.anyOf(priceFuture).thenAccept((obj) -> System.out.println("fastest anyOf done " + obj));
//        System.out.println("aaa");


        //  method 3
        CompletableFuture<Double> aa = CompletableFuture.supplyAsync(() -> new Shop("shop3").getPice("product"), executor);
        System.out.println(" supplyAsync price " + aa.get());



    }


}
