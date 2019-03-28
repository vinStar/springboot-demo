package com.example.springboot.controller;

import com.example.springboot.config.TestBean;
import com.example.springboot.config.TestBean2;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import okhttp3.*;
import okhttp3.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;

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
    TestBean testBean;
    @Autowired
    TestBean2 testBean2;

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

    @ApiOperation(value = "sayHi", notes = "sai hi", produces = "application/json")
    @RequestMapping(value = "/sayHi", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "string", name = "hello", value = "说的内容", required = true),
    })
    public Map<String, String> sayHi(@RequestParam("hello") String hello) {
        Map<String, String> map = new HashMap<>();
        map.put("hello", hello);
        map.put("bean", testBean.test().getName());
        map.put("bean", testBean2.test().getName());

        return map;
    }


    private class User {
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


}


