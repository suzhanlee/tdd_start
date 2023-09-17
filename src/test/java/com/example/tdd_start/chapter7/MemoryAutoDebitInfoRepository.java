package com.example.tdd_start.chapter7;

import java.util.HashMap;
import java.util.Map;

public class MemoryAutoDebitInfoRepository extends AutoDebitInfoRepository {

    private Map<String, AutoDebitInfo> infos = new HashMap<>();

    @Override
    public void save(AutoDebitInfo info) {
        infos.put(info.getUserName(), info);
    }

    @Override
    public AutoDebitInfo findOne(String userName) {
        return infos.get(userName);
    }
}
