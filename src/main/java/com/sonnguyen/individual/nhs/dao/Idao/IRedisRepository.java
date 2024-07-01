package com.sonnguyen.individual.nhs.dao.Idao;

public interface IRedisRepository{
    void put(String key, String value);
    String get(String key);
}
