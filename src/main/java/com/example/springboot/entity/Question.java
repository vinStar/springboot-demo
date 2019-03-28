package com.example.springboot.entity;

import lombok.Data;

import java.util.HashMap;

/**
 * Created by vin on 2019/3/5.
 */
@Data
public class Question {

    //题库，题库随机抽取50题形成考卷，一场考试四种类型的考卷，每种类型的考卷题序不同。

    HashMap<String, String> quesBody;// q num ,q body
    HashMap<String, String> quesAnswer; // ans num ,ans body
//    String quesBody;
//    String quesAnswer;

}
