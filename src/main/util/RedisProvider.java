package util;

import redis.clients.jedis.Jedis;

public class RedisProvider {
    private static final Jedis jedis;

    static {
        try {
            jedis = new Jedis();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Jedis getSession() {
        return jedis;
    }
}
