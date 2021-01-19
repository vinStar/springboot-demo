package com.example.springboot.generics;

public interface UserOperation<DATA> {
    long getId();

    DATA getData();

    long getUserId();
}
