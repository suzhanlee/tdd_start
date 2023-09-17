package com.example.tdd_start;

import java.time.LocalDate;
import java.time.YearMonth;

public class Main {

    public static void main(String[] args) {

        YearMonth month = YearMonth.from(LocalDate.of(2019,8,21));
        System.out.println("month = " + month);
        LocalDate localDate = month.atDay(1);
        System.out.println("localDate = " + localDate);
        LocalDate localDate1 = month.atDay(month.lengthOfMonth());
        System.out.println("localDate1 = " + localDate1);

        LocalDate localDate2 = localDate.withDayOfMonth(3);
        System.out.println("localDate2 = " + localDate2);

        int dayOfMonth = localDate.getDayOfMonth();
        System.out.println("dayOfMonth = " + dayOfMonth);
    }

}
