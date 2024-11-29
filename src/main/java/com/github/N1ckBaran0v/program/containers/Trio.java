package com.github.N1ckBaran0v.program.containers;

import org.jetbrains.annotations.NotNull;

public class Trio<K, V> {
    public final K first;
    public final K second;
    public final V third;

    public Trio(@NotNull K first, @NotNull K second, @NotNull V third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }
}
