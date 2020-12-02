package org.arunm;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisBackedCache {

    private String address;
    private Integer port;
    private JedisPool jedisPool;

    public RedisBackedCache(String address, Integer port) {
        this.address = address;
        this.port = port;

        this.jedisPool = new JedisPool(this.address,this.port);
    }

    public void put(String key, String value) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.set(key,value);
        }
    }

    public String get(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
           return jedis.get(key);
        }
    }
}


