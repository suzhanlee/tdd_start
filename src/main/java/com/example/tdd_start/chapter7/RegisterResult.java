package com.example.tdd_start.chapter7;

import lombok.Getter;

@Getter
public class RegisterResult {

    private CardValidity validity;

    public RegisterResult(CardValidity validity) {
        this.validity = validity;
    }

    public static RegisterResult error(CardValidity validity) {
        return new RegisterResult(CardValidity.INVALID);
    }

    public static RegisterResult success() {
        return new RegisterResult(CardValidity.VALID);
    }
}
