package com.example.tdd_start.chapter3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ExpiryDateCalculatorTest {

    private void assertExpiryDate(PayData payData, LocalDate expectedExpiryDate) {
        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        LocalDate realExpiryDate = cal.calculateExpiryDate(payData);
        assertEquals(expectedExpiryDate, realExpiryDate);
    }

    @Test
    @DisplayName("만원 납부하면 한달 뒤가 만료일이 됨")
    void calculateExpiryDate() {
        assertExpiryDate(
            PayData.builder()
                .billingDate(LocalDate.of(2019, 3, 1))
                .payAmount(10_000)
                .build(),
            LocalDate.of(2019, 4, 1));
        assertExpiryDate(
            PayData.builder()
                .billingDate(LocalDate.of(2019, 5, 5))
                .payAmount(10_000)
                .build(),
            LocalDate.of(2019, 6, 5));
    }

    @Test
    @DisplayName("납부일과 한달 뒤 일자가 같지 않음")
    void calculateExpiryDateV2() {
        assertExpiryDate(
            PayData.builder()
                .billingDate(LocalDate.of(2019, 1, 31))
                .payAmount(10_000)
                .build(),
            LocalDate.of(2019, 2, 28));
        assertExpiryDate(
            PayData.builder()
                .billingDate(LocalDate.of(2019, 5, 31))
                .payAmount(10_000)
                .build(),
            LocalDate.of(2019, 6, 30));
        assertExpiryDate(
            PayData.builder()
                .billingDate(LocalDate.of(2020, 1, 31))
                .payAmount(10_000)
                .build(),
            LocalDate.of(2020, 2, 29));
    }

    @Test
    @DisplayName("예외상황 - 첫 납부일과 만료일 일자가 다를때 만원 납부")
    void calculateExpiryDateV3() {
        // given
        PayData payData = PayData.builder()
            .firstBillingDate(LocalDate.of(2019, 1, 31))
            .billingDate(LocalDate.of(2019, 2, 28))
            .payAmount(10_000)
            .build();

        PayData payData2 = PayData.builder()
            .firstBillingDate(LocalDate.of(2019, 1, 30))
            .billingDate(LocalDate.of(2019, 2, 28))
            .payAmount(10_000)
            .build();

        // when then
        assertExpiryDate(payData, LocalDate.of(2019, 3, 31));
        assertExpiryDate(payData2, LocalDate.of(2019, 3, 30));
    }

    @Test
    @DisplayName("이만원 이상 납부하면 비례해서 만료일 계산")
    void calculateExpiryDateV4() {
        assertExpiryDate(PayData.builder()
                .billingDate(LocalDate.of(2019, 3, 1))
                .payAmount(20_000)
                .build(),
            LocalDate.of(2019, 5, 1));

        assertExpiryDate(PayData.builder()
                .billingDate(LocalDate.of(2019, 3, 1))
                .payAmount(30_000)
                .build(),
            LocalDate.of(2019, 6, 1));
    }

    @Test
    @DisplayName("예외상황 - 첫 납부일과 만료일자가 다를때 이만원 이상 납부")
    void calculateExpiryDateV5() {
        assertExpiryDate(PayData.builder()
                .firstBillingDate(LocalDate.of(2019, 1, 31))
                .billingDate(LocalDate.of(2019, 2, 28))
                .payAmount(20_000)
                .build(),
            LocalDate.of(2019, 4, 30));
    }

    @Test
    @DisplayName("십만원을 납부하면 1년 제공")
    void calculateExpiryDateV6(){
        assertExpiryDate(PayData.builder()
                .billingDate(LocalDate.of(2019, 1, 28))
                .payAmount(100_000)
                .build(),

            LocalDate.of(2020, 1, 28));
    }
}
