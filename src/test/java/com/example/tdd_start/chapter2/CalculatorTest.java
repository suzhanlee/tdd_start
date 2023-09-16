package com.example.tdd_start.chapter2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.tdd_start.chapter2.Calculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    @Test
    @DisplayName("계산기는 더하기를 할 수 있습니다.")
    void plus(){
        // given // when
        int result = Calculator.plus(1, 2);

        // then
        assertEquals(3, result);
        assertEquals(5, Calculator.plus(4, 1));
    }

}
