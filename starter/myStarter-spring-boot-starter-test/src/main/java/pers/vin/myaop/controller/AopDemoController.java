package pers.vin.myaop.controller;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.vin.myaop.function.LogExecutionTime;
import pers.vin.myaop.function.RequestTimeAspect;


/**
 * Created by vin on 2019/3/29.
 */
@RestController
@RequestTimeAspect
public class AopDemoController {


    @RequestMapping("/aop")
    public String aop() {

//        try {
//            //Thread.sleep(1100);
//            return "ok";
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return "ok";
    }


    @LogExecutionTime
    @RequestMapping("/aopAspect")
    public String aopAspect() {

        return "ok";
    }
}
