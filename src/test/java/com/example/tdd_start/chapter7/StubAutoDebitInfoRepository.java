package com.example.tdd_start.chapter7;

public class StubAutoDebitInfoRepository extends AutoDebitInfoRepository{

    @Override
    public AutoDebitInfo findOne(String userName) {
        return null;
    }

    @Override
    public void save(AutoDebitInfo newInfo) {

    }
}
