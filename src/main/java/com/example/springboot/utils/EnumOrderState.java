package com.example.springboot.utils;

/**
 * Created by vin on 2019/3/22.
 */
public enum EnumOrderState {

    UNPAY(-1, "未支付"),
    PAY(0, "已支付"),
    DELIVERY(1, "已处理"),
    RENEWAL(2, "已续费"),
    COMPLETE(3, "已完成"),

    CANCEL(5, "已取消"),

    END(100, "未定义");

    private Integer index;
    private String des;

    private EnumOrderState(Integer index, String des) {
        this.index = index;
        this.des = des;
    }

    public int value() {
        return this.index;
    }

    public String des() {
        return this.des;
    }
}
