package com.sonnguyen.individual.nhs.Repository.IRepository;

public interface IRedisRepository{
    void put(String key, String value);
    String get(String key);
}
