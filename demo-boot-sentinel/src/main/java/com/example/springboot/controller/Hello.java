package com.example.springboot.controller;

import com.example.springboot.service.MyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;


/**
 * Created by vin on 13/01/2018.
 */
@CrossOrigin(origins = "http://localhost:63342/", maxAge = 3600)//单个controller 允许跨域
@RestController
@Api(value = "Hello", description = "测试HelloController")
public class Hello {

    @Autowired
    MyService myService;


    @CrossOrigin("*")
    //默认为 */*
    //支持xml,json 设置 produces = "application/xml,application/json")
    @ApiOperation(value = "获取用户", notes = "谁来决定你的数据返回类型json|xml?", produces = "application/xml,application/json")
//, produces = "application/json,application/xml"
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public User index() {

        List<String> testList = new ArrayList<String>();
        testList.add("aa");
        testList.add("bb");


        User user = new User();
        user.setName("Grace");
        user.setTestList(testList);

        return user;
    }


    static class User {
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<String> getTestList() {
            return testList;
        }

        public void setTestList(List<String> testList) {
            this.testList = testList;
        }

        String name;

        List<String> testList;

    }

    @ApiOperation(value = "okhttp", notes = "okhttp", produces = "application/json")
    @RequestMapping(value = "/getStars", method = RequestMethod.GET)
    public String getStars() {

        String str = null;
        OkHttpClient client = new OkHttpClient();
        Request request = null;
        try {
            request = new Request.Builder().
                    url("https://api.github.com/_private/browser/stats")
                    .addHeader("User-Agent", "mozilla").build();
            Response response = client.newCall(request).execute();
            str = response.body().string();
            System.out.println(str);
        } catch (IOException ioe) {
            System.out.println(ioe.toString());
        } finally {
            request = null;
            client = null;
        }

        return str;


    }


    @ApiOperation(value = "test sentinel", notes = "test sentinel", produces = "application/json")
    @RequestMapping(value = "/sentinel", method = RequestMethod.GET)
    public void get() {

        String str = null;

        myService.doSomething();


    }


}


