package com.example.tdd_start.chapter7;

import lombok.Getter;

@Getter
public class AutoDebitReq {

    private String carNumber;
    private String userName;

    public AutoDebitReq(String userName, String carNumber) {
        this.userName = userName;
        this.carNumber = carNumber;
    }
}
