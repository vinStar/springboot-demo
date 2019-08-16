package com.example.demo.feign;

import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lt
 * @date 2019/7/23
 */
@FeignClient(value = "${feign.my-feign-name}")
@Service
public interface MyFeign {

    @RequestMapping(path = "500")
    String timeout500();


    @RequestMapping("1500")
    String timeout1500();

    @RequestMapping("2000")
    String timeout2000();


    @RequestMapping("5000")
    String timeout5000();

    @RequestMapping("7000")
    String timeout7000();


    @RequestMapping("11000")
    String timeout11000();


    @RequestMapping("61000")
    String timeout61000();


}
