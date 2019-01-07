package com.example.demo.hc.service;

import java.util.Set;

public interface RedisService {

    /**
     * 获取过期时间
     */
    Long ttl(final String key);

    /**
     * set存数据
     *
     * @param key
     * @param value
     * @return
     */


    boolean set(String key, String value);

    /**
     * get获取数据
     *
     * @param key
     * @return
     */
    String get(String key);

    /**
     * 设置有效天数
     *
     * @param key
     * @param expire
     * @return
     */
    boolean expire(String key, long expire);

    /**
     * 移除数据
     *
     * @param key
     * @return
     */
    boolean remove(String key);

    boolean zset(final String key, final Double d, final String value);

    Set<byte[]> zRangeByLex(final String key);

    Set<byte[]> zRangeByScore(final String key);
}
