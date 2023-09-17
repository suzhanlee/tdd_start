package com.example.tdd_start.chapter7;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AutoDebitRegister {

    private CardNumberValidator validator;
    private AutoDebitInfoRepository repository;

    public AutoDebitRegister(CardNumberValidator validator, AutoDebitInfoRepository repository) {
        this.validator = validator;
        this.repository = repository;
    }

    public RegisterResult register(AutoDebitReq req) {
        CardValidity validity = validator.validate(req.getCarNumber());
        if (validity != CardValidity.VALID) {
            return RegisterResult.error(validity);
        }
        AutoDebitInfo info = repository.findOne(req.getUserName());
        if (info != null) {
            info.changeCarNumber(req.getCarNumber());
        } else {
            AutoDebitInfo newInfo = new AutoDebitInfo(req.getUserName(), req.getCarNumber(),
                LocalDateTime.now());
            repository.save(newInfo);
        }
        return RegisterResult.success();
    }
}
