package com.example.tdd_start.chapter7;

public interface UserRepository {

    void save(User user);

    User findById(String id);
}
