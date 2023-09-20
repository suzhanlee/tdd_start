package com.example.tdd_start.chapter7;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

public class UserRegisterMockTest {

    private UserRegister userRegister;
    private WeakPasswordChecker mockPasswordChecker = Mockito.mock(WeakPasswordChecker.class);
    private MemoryUserRepository fakeRepository = new MemoryUserRepository();
    private EmailNotifier mockEmailNotifier = Mockito.mock(EmailNotifier.class);

    @BeforeEach
    void setUp() {
        userRegister = new UserRegister(mockPasswordChecker, fakeRepository, mockEmailNotifier);
    }

    @Test
    @DisplayName("stub 사용 : 약한 암호면 가입 실패")
    void weakPassword() {
//        mockPasswordChecker.setWeak(true);
        // 모의 객체를 이용해 stub을 대신한다. -> 모의 객체가 다음과 같이 행동하도록 설정한다.
        BDDMockito.given(mockPasswordChecker.checkPasswordWeak("pw")).willReturn(true);

        assertThrows(WeakPasswordException.class, () -> {
            userRegister.register("id", "pw", "email");
        });
    }

    @Test
    @DisplayName("모의 객체가 기대한 대로 불렀는지 검증하는 코드 : 회원 가입시 암호 검사 수행함")
    void checkPassword() {
        userRegister.register("id", "pw", "email");

        BDDMockito.then(mockPasswordChecker)
            .should()
            .checkPasswordWeak(anyString());
    }

    @Test
    @DisplayName("Spy 대역 사용 : 가입하면 메일을 전송함")
    void whenRegisterThenSendEmail() {
        userRegister.register("id", "pw", "email@email.com");

        // ArgumentCaptor은 모의 객체로 메서드를 호출할 때 전달한 객체를 담는 기능을 제공
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        BDDMockito.then(mockEmailNotifier)
            .should().sendRegisterEmail(captor.capture());

        // ArgumentCaptor가 보관한 인자 꺼내기
        String realEmail = captor.getValue();
//        assertEquals("email@email.com", mockEmailNotifier.getEmail());
        assertEquals("email@email.com", realEmail);
    }
}
