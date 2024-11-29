package com.github.N1ckBaran0v.program.containers;

import org.jetbrains.annotations.NotNull;

public interface Map2D <K, V> extends Iterable<Trio<K, V>> {
    void put(@NotNull K key1, @NotNull K key2, @NotNull V value);
    boolean contains(@NotNull K key1, @NotNull K key2);
    V get(@NotNull K key1, @NotNull K key2);
    void remove(@NotNull K key1, @NotNull K key2);
    int size();
}
