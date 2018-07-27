package com.wangzai.map;

public interface WZMap<K, V> {

    V get(K k);

    V put(K key, V value);

    int size();

    public interface Entry<K, V> {
        K getKey();

        V getValue();
    }

}
