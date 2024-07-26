package com.sonnguyen.individual.nhs.dao.idao;

public interface IRedisRepository{
    void put(String key, String value);
    String get(String key);
}
