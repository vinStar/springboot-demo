package com.example.springboot.controller;


import com.example.springboot.utils.DES3Util;
import com.example.springboot.utils.DESEncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class DesController {


    @RequestMapping("encrypt")
    public String encrypt(String input) {

        log.info("input : " + input);
        String k = "1fd2s1f5sd5fsfdf";
        String str = DES3Util.encrypt(input, k);
        log.info("key : " + k);

        return str;

    }

    @RequestMapping("decrypt")
    public String decrypt(String pass) {

        log.info("pass : " + pass);
        String str = DES3Util.decrypt(pass, "1fd2s1f5sd5fsfdf");
        log.info("str : " + str);
        return str;
    }

    @RequestMapping("encrypt2")
    public String encrypt2(String input) {

        log.info("input : " + input);
        String k = "1fd2s1f5sd5fsfdf";
        log.info("key : " + k);
        String str = DESEncryptUtil.encrypt(input, k);


        log.info("pass {0}", str);

        return str;

    }

    @RequestMapping("decrypt2")
    public String decrypt2(String pass) {

        log.info("pass : " + pass);
        String k = "1fd2s1f5sd5fsfdf";
        log.info("key : " + k);
        String str = DESEncryptUtil.decrypt(pass, k);
        log.info("str : " + str);
        return str;
    }


}
