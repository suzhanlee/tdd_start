package com.example.tdd_start.chapter3;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PayData {

    private LocalDate firstBillingDate;
    private LocalDate billingDate;
    private int payAmount;

    private PayData() {}

    public PayData(LocalDate firstBillingDate, LocalDate billingDate, int payAmount) {
        this.firstBillingDate = firstBillingDate;
        this.billingDate = billingDate;
        this.payAmount = payAmount;
    }
}
