package com.github.N1ckBaran0v.program.containers;

public interface Map2D <K, V> {
    void put(K key1, K key2, V value);
    boolean contains(K key1, K key2);
    V get(K key1, K key2);
    void remove(K key1, K key2);
}
