package com.github.N1ckBaran0v.program.containers;

import java.util.HashMap;
import java.util.Map;

public class HashMap2D<K, V> implements Map2D<K, V> {
    private final Map<K, Map<K, V>> inner = new HashMap<>();

    @Override
    public void put(K key1, K key2, V value) {
        if (inner.containsKey(key1)) {
            inner.get(key1).put(key2, value);
        } else {
            var map = new HashMap<K, V>();
            map.put(key2, value);
            inner.put(key1, map);
        }
    }

    @Override
    public boolean contains(K key1, K key2) {
        return inner.containsKey(key1) && inner.get(key1).containsKey(key2);
    }

    @Override
    public V get(K key1, K key2) {
        var map = inner.get(key1);
        return map == null ? null : map.get(key2);
    }

    @Override
    public void remove(K key1, K key2) {
        inner.get(key1).remove(key2);
    }
}
