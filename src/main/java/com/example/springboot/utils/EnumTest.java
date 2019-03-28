package com.example.springboot.utils;

/**
 * Created by vin on 2019/3/22.
 */
public enum EnumTest {

    A("text1"),
    B("text2"),
    C("text3"),
    D("text4");

    private String text;

    EnumTest(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public boolean isA() {
        return this.equals(EnumTest.A);
    }


    public static EnumTest fromString(String text) {
        for (EnumTest b : EnumTest.values()) {
            if (b.text.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}


