package com.example.springboot.utils;

/**
 * Created by vin on 2019/3/8.
 */
public class TryReturnFinally {
    /*
     1、左++ 在return返回前执行
     2、右++ 在return返会后执行
     3、return 返回对象存储在临时变量（副本）
        finally 语句执行后对副本无影响
 */
    public static void main(String[] args) {

        // return ++b  : 2
        // return b++  : 1
        System.out.println("return : " + test1());
        System.out.println("return : " + test2());

    }

    public static int test1() {

        int b = 1;

        try {

            System.out.println("try block");
            // return ++b  : 2
            // return b++  : 1
            return ++b;

        } catch (Exception e) {

            b = 10;
            System.out.println("catch block");

        } finally {
            // b = 0;
            // return 2

            ++b;
            System.out.println("finally block and b is :" + b);

        }
        System.out.println("last block");
        return b;
    }

    public static String test2() {

        String b = "abc";

        try {

            System.out.println("try block");

            return b + "e";

        } catch (Exception e) {

            b += "f";
            System.out.println("catch block");

        } finally {

            // return abce

            b = null;
            System.out.println("finally block and b is :" + b);

        }
        System.out.println("last block");
        return b;
    }

}
