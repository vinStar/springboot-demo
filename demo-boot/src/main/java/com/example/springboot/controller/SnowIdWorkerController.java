package com.example.springboot.controller;


import com.example.springboot.utils.SnowflakeIdWorker;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SnowIdWorkerController {


    @RequestMapping("id")
    public long getId() {
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1, 1);

        long id = idWorker.nextId();

        return id;

    }

}
