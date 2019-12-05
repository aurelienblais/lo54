package util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisProvider {
    private static final JedisPool jedis = new JedisPool(new JedisPoolConfig(), "localhost");;

    public static JedisPool getPool() {
        return jedis;
    }

    public static Jedis getSession() {
        return jedis.getResource();
    }
}
