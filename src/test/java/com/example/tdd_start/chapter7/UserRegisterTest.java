package com.example.tdd_start.chapter7;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserRegisterTest {

    private UserRegister userRegister;
    private StubWeakPasswordChecker stubPasswordChecker = new StubWeakPasswordChecker();
    private MemoryUserRepository fakeRepository = new MemoryUserRepository();
    private SpyEmailNotifier spyEmailNotifier = new SpyEmailNotifier();

    @BeforeEach
    void setUp() {
        userRegister = new UserRegister(stubPasswordChecker, fakeRepository, spyEmailNotifier);
    }

    @Test
    @DisplayName("stub 사용 : 약한 암호면 가입 실패")
    void weakPassword() {
        stubPasswordChecker.setWeak(true);
        assertThrows(WeakPasswordException.class, () -> {
            userRegister.register("id", "pw", "email");
        });
    }

    @Test
    @DisplayName("가짜 대역 사용 : 이미 같은 ID가 존재하면 가입 실패")
    void dupIdExists() {
        fakeRepository.save(new User("id", "pw1", "email@email.com"));
        assertThrows(DupIdException.class, () -> {
            userRegister.register("id", "pw2", "email");
        });
    }

    @Test
    @DisplayName("가짜 대역 사용 : 같은 ID가 없으면 가입 성공함")
    void noDupId_RegisterSuccess() {
        userRegister.register("id", "pw", "email");

        User savedUser = fakeRepository.findById("id");
        assertEquals("id", savedUser.getId());
        assertEquals("email", savedUser.getEmail());
    }

    @Test
    @DisplayName("Spy 대역 사용 : 가입하면 메일을 전송함")
    void whenRegisterThenSendEmail() {
        userRegister.register("id", "pw", "email@email.com");

        assertTrue(spyEmailNotifier.isCalled());
        assertEquals("email@email.com", spyEmailNotifier.getEmail());
    }
}
