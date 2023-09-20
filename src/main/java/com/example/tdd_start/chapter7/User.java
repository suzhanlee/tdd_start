package com.example.tdd_start.chapter7;

import lombok.Getter;

@Getter
public class User {

    private final String id;
    private final String pw;
    private final String email;

    public User(String id, String pw, String email) {
        this.id = id;
        this.pw = pw;
        this.email = email;
    }
}
