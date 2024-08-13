package com.github.N1ckBaran0v.program.containers;

public class Trio<K, V> {
    public final K first;
    public final K second;
    public final V third;

    public Trio(K first, K second, V third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }
}
