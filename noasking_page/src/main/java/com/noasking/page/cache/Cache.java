package com.noasking.page.cache;

/**
 * 简单的缓存接口
 *
 */
public interface Cache<K, V> {
    /**
     * 获取缓存值，不存在则返回null
     *
     * @param key
     * @return
     */
    V get(K key);

    /**
     * 缓存KV
     *
     * @param key
     * @param value
     */
    void put(K key, V value);
}
