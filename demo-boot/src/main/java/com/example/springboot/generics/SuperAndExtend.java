package com.example.springboot.generics;

import java.util.ArrayList;

public class SuperAndExtend {


    // step 1  what's generics  , like : ArrayList
    ArrayList<String> strList = new ArrayList<String>();
    ArrayList<Integer> intList = new ArrayList<Integer>();
    ArrayList<Double> doubleList = new ArrayList<Double>();

    /**
     * 类型擦除
     */
    static void abrasion() {

        Class a = new ArrayList<Integer>().getClass();
        Class b = new ArrayList<String>().getClass();

        System.out.println(a == b);  // true

    }

    //can't do
    //if(obj instanceof T);
    //T t = new T();
    //T[] ts = new T[10];

    //instanceof  用来测试一个对象是否为一个类/子类的实例
    //boolean result = obj instanceof Class

    //isInstance

    public static void main(String[] args) {
        abrasion();



    }

}
