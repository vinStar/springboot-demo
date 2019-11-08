package com.example.demo.Controller;


import com.example.demo.bean.bo.GoodsBO;

import com.example.demo.feign.MyFeign;
import com.example.demo.inf.beans.FeignBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@Api(tags = "feign-client")
public class FeignController {


    @Autowired
    MyFeign myFeign;


    @ApiOperation(value = "timeout 500", httpMethod = "GET")
    @RequestMapping(path = "500")
    public String timeout500() {

        try {
            myFeign.timeout500();
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
            log.info("begin request");
            myFeign.timeout1500();
            log.info("end request");

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
            myFeign.timeout2000();
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
            myFeign.timeout5000();
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
            myFeign.timeout7000();
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
            myFeign.timeout11000();
        } catch (Exception e) {
            log.error("error : {}", e);

            return "error";
        }

        return "synonym";
    }

    @ApiOperation(value = "timeout 61000", httpMethod = "GET")
    @RequestMapping("61000")
    public String timeout61000() {


        try {
            myFeign.timeout61000();
        } catch (Exception e) {
            log.error("error : {}", e);

            return "error";
        }

        return "synonym";
    }

    @ApiOperation(value = "shutdown 60000", httpMethod = "GET")
    @RequestMapping("60000")
    public String shutdown60000() {


        try {
            myFeign.shutdown60000();
        } catch (Exception e) {
            log.error("error : {}", e);

            return "error";
        }

        return "synonym shutdown60000";
    }

    @ApiOperation(value = "bo set ", httpMethod = "POST")
    @RequestMapping("boset")
    public String boSet(
            @ApiParam(name = "商品参数", value = "传入json格式", required = true)
            @RequestBody GoodsBO goodsBO) {

        try {
            log.info(goodsBO.toString());
        } catch (Exception e) {
            log.error("error : {}", e);

            return "error";
        }

        return goodsBO.toString();
    }


    @ApiOperation(value = "url", httpMethod = "GET")
    @RequestMapping(value = "url/{a}/{b}", method = RequestMethod.GET)
    public String url(
            @ApiParam(name = "a")
            @PathVariable Integer a,
            @ApiParam(name = "b")
            @PathVariable Integer b,
            @ApiParam(name = "c", required = true, hidden = true)
                    Integer c
    ) {

        try {
            log.info(a + "");
            log.info(b + "");
            log.info(c + "");
        } catch (Exception e) {
            log.error("error : {}", e);

            return "error";
        }

        return "synonym";
    }

    @ApiOperation(value = "testFeignBean  Long Integer List<Long> List<Integer>", httpMethod = "GET")
    @RequestMapping("testFeignBean")
    public FeignBean testFeignBean() {

        FeignBean bean = null;
        try {
            bean = myFeign.testFeignBean();

            log.info(bean.toString());
        } catch (Exception e) {
            log.error("error : {}", e);

            return bean;
        }

        return bean;
    }


}
