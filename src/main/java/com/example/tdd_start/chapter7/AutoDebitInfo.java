package com.example.tdd_start.chapter7;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class AutoDebitInfo {

    private String userName;
    private String carNumber;

    public AutoDebitInfo(String userName, String carNumber, LocalDateTime now) {
        this.userName = userName;
        this.carNumber = carNumber;
    }

    public void changeCarNumber(String carNumber) {

    }
}
