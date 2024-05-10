//package com.sonnguyen.individual.nhs.Repository;
//
//import com.sonnguyen.individual.nhs.Repository.IRepository.IRedisRepository;
//import io.lettuce.core.RedisClient;
//import io.lettuce.core.api.StatefulRedisConnection;
//import io.lettuce.core.api.async.RedisAsyncCommands;
//
//public class RedisRepository implements IRedisRepository {
//    static final RedisClient redisClient = RedisClient.create("redis://localhost:6379");
//    static StatefulRedisConnection<String,String> redisConnection;
//    static RedisAsyncCommands<String,String> redisAsyncCommands;
//    static {
//        redisConnection=redisClient.connect();
//        redisAsyncCommands=redisConnection.async();
//    }
//
//    @Override
//    public void put(String key, String value) {
//        redisAsyncCommands.set(key,value);
//    }
//
//    @Override
//    public String get(String key) {
//        return redisAsyncCommands.get(key);
//    }
//}
