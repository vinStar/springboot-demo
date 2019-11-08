package com.example.demo.Controller;


import com.example.demo.inf.beans.FeignBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@Slf4j
@Api(tags = "timeout")
public class ServiceController {


    @ApiOperation(value = "timeout 500", httpMethod = "GET")
    @RequestMapping(path = "500")
    public String timeout500() {

        try {
            log.info("begin timeout500");
            Thread.sleep(500L);
            log.info("end timeout500");
        } catch (Exception e) {
            log.error("error : {}", e);

            return "error";
        }

        return "synonym";
    }

    @ApiOperation(value = "timeout 1500", httpMethod = "GET")
    @RequestMapping("1500")
    public String timeout1500() {

        try {
            log.info("begin timeout1500");
            Thread.sleep(1500L);
            log.info("end timeout1500");
        } catch (Exception e) {
            log.error("error : {}", e);

            return "error";
        }

        return "synonym";
    }

    @ApiOperation(value = "timeout 2000", httpMethod = "GET")
    @RequestMapping("2000")
    public String timeout2000() {

        try {
            log.info("begin timeout2000");
            Thread.sleep(2000L);
            log.info("end timeout2000");
        } catch (Exception e) {
            log.error("error : {}", e);

            return "error";
        }

        return "synonym";
    }

    @ApiOperation(value = "timeout 5000", httpMethod = "GET")
    @RequestMapping("5000")
    public String timeout5000() {

        try {
            log.info("begin timeout5000");
            Thread.sleep(5000L);
            log.info("end timeout5000");
        } catch (Exception e) {
            log.error("error : {}", e);

            return "error";
        }

        return "synonym";
    }

    @ApiOperation(value = "timeout 7000", httpMethod = "GET")
    @RequestMapping("7000")
    public String timeout7000() {

        try {
            log.info("begin timeout7000");
            Thread.sleep(7000L);
            log.info("end timeout7000");
        } catch (Exception e) {
            log.error("error : {}", e);

            return "error";
        }

        return "synonym";
    }

    @ApiOperation(value = "timeout 11000", httpMethod = "GET")
    @RequestMapping("11000")
    public String timeout11000() {

        try {
            log.info("begin timeout11000");
            Thread.sleep(11000L);
            log.info("end timeout11000");
        } catch (Exception e) {
            log.error("error : {}", e);

            return "error";
        }

        return "synonym 11000";
    }

    @ApiOperation(value = "timeout 61000", httpMethod = "GET")
    @RequestMapping("61000")
    public String timeout61000() {


        try {
            log.info("begin timeout61000");
            Thread.sleep(61000L);
            log.info("end timeout61000");
        } catch (Exception e) {
            log.error("error : {}", e);

            return "error";
        }

        return "synonym 61000";
    }


    @ApiOperation(value = "testFeignBean  Long Integer List<Long> List<Integer>", httpMethod = "GET")
    @RequestMapping("testFeignBean")
    public FeignBean testFeignBean() {

        FeignBean feignBean = new FeignBean();
        feignBean.setS1("test");
        feignBean.setInt1(100);
        feignBean.setL1(100L);

        List<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        integerList.add(2);

        List<Long> longList = new ArrayList<>();
        longList.add(1L);
        longList.add(2L);
        feignBean.setIntegerList(integerList);
        feignBean.setLongList(longList);
        feignBean.setDate1(new Date());


        return feignBean;
    }

    @ApiOperation(value = "前端 testFeignBean  Long Integer List<Long> List<Integer>", httpMethod = "POST")
    @RequestMapping(value = "frontFeignBean", method = RequestMethod.POST)
    public FeignBean frontFeignBean(
            @ApiParam(name = "long 类型转换 ")
            @RequestBody FeignBean feignBean) {

        log.info(feignBean.toString());
        return feignBean;
    }


}



