package com.example.springboot.controller;

import com.example.springboot.entity.Question;
import com.example.springboot.utils.Concurrent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by vin on 2019/3/5.
 */
@Slf4j
@RestController
@RequestMapping("/qa")
public class QaController {

    public QaController() {
        System.out.println("construct");
        System.out.println(Runtime.getRuntime().maxMemory() / 1024 / 1024);
        System.out.println(Runtime.getRuntime().totalMemory() / 1024 / 1024);
        System.out.println(" ");
    }

    static HashMap<String, String> staticMap;

    //todo avoid of repeat create instance,to verify the different gc performance
    static {
        staticMap = new HashMap<>();
    }

    @RequestMapping(path = "/question",
            produces = {"application/json; charset=UTF-8"})
    public Question getQuestion(int qType, int index) {

        Question question = new Question();

        HashMap<String, String> queBody = staticMap;// new HashMap<String, String>
        HashMap<String, String> queAnswer = staticMap;
        queBody.put("1", "how do you do? What made you want to look up QA? Please tell us where you read or heard it ");
        queAnswer.put("A", "where you read or heard it");
        queAnswer.put("B", "What made you want to look up QA?");
        queAnswer.put("C", "where you read or heard it");
        queAnswer.put("D", "What made you want to look up QA?");

        question.setQuesBody(queBody);
        question.setQuesAnswer(queAnswer);

//        question.setQuesBody(String.valueOf(qType));
//        question.setQuesAnswer(String.valueOf(index));

        queAnswer = null;
        queBody = null;
        log.info("maxMemory Xmx {}", Runtime.getRuntime().maxMemory() / 1024 / 1024);
        log.info("useMemory Xms {}", Runtime.getRuntime().totalMemory() / 1024 / 1024);
        System.out.println(" ");
        return question;

    }

    @RequestMapping("/submit")
    public String post(int qType, int index) {

        String strResult = "1";
        // simulate db time
        try {
            Thread.sleep(50L);
        } catch (InterruptedException e) {
            e.printStackTrace();
            strResult = "0";
        }

        return "1";
    }

    @RequestMapping("/request")
    public void request(int t, int c, String url) {
        log.info("url {}", url);

        try {
            Concurrent.request(t, c, url);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
