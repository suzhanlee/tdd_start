package com.example.tdd_start.chapter2;

import static com.example.tdd_start.chapter2.PasswordStrength.INVALID;
import static com.example.tdd_start.chapter2.PasswordStrength.NORMAL;
import static com.example.tdd_start.chapter2.PasswordStrength.STRONG;
import static com.example.tdd_start.chapter2.PasswordStrength.WEAK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.tdd_start.chapter2.PasswordStrength;
import com.example.tdd_start.chapter2.PasswordStrengthMeter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PasswordStrengthMeterTest {

    private PasswordStrengthMeter meter = new PasswordStrengthMeter();

    private void assertStrength(String password, PasswordStrength expStr) {
        PasswordStrength result = meter.meter(password);
        assertEquals(expStr, result);
    }

    @Test
    @DisplayName("암호가 모든 조건을 충족하면 암호 강도는 강함이어야 함")
    void meetsAllCriteria_Then_Strong() {
        assertStrength("ab12!@AB", STRONG);
        assertStrength("abc1!Add", STRONG);
    }

    @Test
    @DisplayName("길이만 8글자 미만이고 나머지 조건은 충족하면 암호 강도는 보통이어야 함")
    void meetsOtherCriteria_except_for_length_Then_Normal() {
        assertStrength("ab12!@A", NORMAL);
        assertStrength("Ab12!c", NORMAL);
    }

    @Test
    @DisplayName("숫자를 포함하지 않고 나머지 조건은 모두 충족하는 경우 암호 강도는 보통이어야 함")
    void meetsOtherCriteria_except_for_number_Then_Normal() {
        assertStrength("ab!@ABqwer", NORMAL);
    }

    @Test
    @DisplayName("값이 없는 경우 유효하지 않음을 반환한다.")
    void nullInput_Then_Invalid() {
        assertStrength(null, INVALID);
    }

    @Test
    @DisplayName("값이 비어있는 경우 유효하지 않음을 반환한다.")
    void emptyInput_Then_Invalid() {
        assertStrength("", INVALID);
    }

    @Test
    @DisplayName("대문자를 포함하지 않고 나머지 조건을 충족하는 경우 암호 강도는 보통이어야 함")
    void meetsOtherCriteria_except_for_Uppercase_Then_Normal() {
        assertStrength("ab12!@df", NORMAL);
    }

    @Test
    @DisplayName("길이가 8글자 이상인 조건만 충족하는 경우 암호 강도는 약함이어야 함")
    void meetsOnlyLengthCriteria_Then_Weak(){
        assertStrength("abdefghi", WEAK);
    }

    @Test
    @DisplayName("숫자 포함 조건만 충족하는 경우 암호 강도는 약함이어야 함")
    void meetsOnlyNumCriteria_Then_Weak(){
        assertStrength("12345", WEAK);
    }

    @Test
    @DisplayName("대문자 포함 조건만 충족하는 경우 암호 강도는 약함이어야 함")
    void meetsOnlyUpperCriteria_Then_Weak(){
        assertStrength("ABZEF", WEAK);
    }

    @Test
    @DisplayName("아무 조건도 충족하지 않는 경우 암호 강도는 약함이어야 함")
    void meetsNoCriteria_Then_Weak(){
        assertStrength("abc", WEAK);
    }
}
