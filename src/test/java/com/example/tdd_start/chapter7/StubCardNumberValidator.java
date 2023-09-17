package com.example.tdd_start.chapter7;

public class StubCardNumberValidator extends CardNumberValidator {

    private String invalidNo;

    public void setInvalidNo(String invalidNo) {
        this.invalidNo = invalidNo;
    }

    @Override
    public CardValidity validate(String carNumber) {
        if (invalidNo != null && invalidNo.equals(carNumber)) {
            return CardValidity.INVALID;
        }

        return CardValidity.VALID;
    }
}
