package com.example.tdd_start.chapter7;

import static com.example.tdd_start.chapter7.CardValidity.INVALID;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AutoDebitRegisterTest {

    private AutoDebitRegister register;
    private StubCardNumberValidator stubValidator;
    private StubAutoDebitInfoRepository stubRepository;
    private MemoryAutoDebitInfoRepository repository;

    @BeforeEach
    void setUp() {
        stubValidator = new StubCardNumberValidator();
//        stubRepository = new StubAutoDebitInfoRepository();
        repository = new MemoryAutoDebitInfoRepository();
        register = new AutoDebitRegister(stubValidator, repository);
    }

    @Test
    void invalidCard() {
        stubValidator.setInvalidNo("123");

        AutoDebitReq req = new AutoDebitReq("user1", "123");
        RegisterResult result = register.register(req);

        assertEquals(INVALID, result.getValidity());
    }

    @Test
    void alreadyRegistered_InfoUpdated() {
        repository.save(new AutoDebitInfo("user1", "123", LocalDateTime.now()));

        AutoDebitReq req = new AutoDebitReq("user1", "123");
        RegisterResult result = register.register(req);

        AutoDebitInfo saved = repository.findOne("user1");
        assertEquals("123", saved.getCarNumber());
    }

    @Test
    void notYetRegistered_newInfoRegistered() {
        AutoDebitReq req = new AutoDebitReq("user1", "123");
        RegisterResult result = register.register(req);

        AutoDebitInfo saved = repository.findOne("user1");
        assertEquals("123", saved.getCarNumber());
    }
}