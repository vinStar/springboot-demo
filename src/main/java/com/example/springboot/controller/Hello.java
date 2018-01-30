package com.example.springboot.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liutong on 13/01/2018.
 */
@CrossOrigin(origins = "http://localhost:63342/", maxAge = 3600)//单个controller 允许跨域
@RestController
public class Hello {

    @CrossOrigin("*")
    //默认为 */*
    //支持xml,json 设置 produces = "application/xml,application/json")
    @ApiOperation(value = "获取用户", notes = "谁来决定你的数据返回类型json|xml?", produces = "application/xml,application/json")//, produces = "application/json,application/xml"
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public User index() {

        List<String> testList = new ArrayList<String>();
        testList.add("aa");
        testList.add("bb");


        User user=new User();
        user.setName("Grace");
        user.setTestList(testList);

        return user;
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

}


