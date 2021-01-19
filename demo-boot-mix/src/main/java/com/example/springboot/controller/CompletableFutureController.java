package com.example.springboot.controller;


import com.example.springboot.completableFuture.Shop;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;


@RestController
@Api(value = "CompletableFuture", description = "测试HelloController")
public class CompletableFutureController {


    @ApiOperation(value = "getPrice", notes = "获取价格 getPrice", produces = "application/json")
    @RequestMapping(value = "/cf", method = RequestMethod.GET)
    public void getPrice() {

        try {
            Shop.test();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
