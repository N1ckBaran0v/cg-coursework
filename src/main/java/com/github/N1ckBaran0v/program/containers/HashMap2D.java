package com.github.N1ckBaran0v.program.containers;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HashMap2D<K, V> implements Map2D<K, V> {
    private final Map<K, Map<K, V>> inner = new ConcurrentHashMap<>();

    public HashMap2D() {
    }

    public HashMap2D(HashMap2D<K, V> other) {
        for (var trio : other) {
            put(trio.first, trio.second, trio.third);
        }
    }

    @Override
    public void put(K key1, K key2, V value) {
        if (inner.containsKey(key1)) {
            inner.get(key1).put(key2, value);
        } else {
            var map = new ConcurrentHashMap<K, V>();
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

    @Override
    public int size() {
        int result = 0;
        for (var submap : inner.values()) {
            result += submap.size();
        }
        return result;
    }

    @Override
    public Iterator<Trio<K, V>> iterator() {
        return new HashMap2DIterator<>(inner);
    }
}
