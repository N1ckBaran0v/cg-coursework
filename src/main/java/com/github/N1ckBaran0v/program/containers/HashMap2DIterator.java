package com.github.N1ckBaran0v.program.containers;

import java.util.Iterator;
import java.util.Map;

class HashMap2DIterator<K, V> implements Iterator<Trio<K, V>> {
    private final Map<K, Map<K, V>> map;
    private final Iterator<K> first;
    private Iterator<K> second;
    private K value1;

    public HashMap2DIterator(Map<K, Map<K, V>> map) {
        this.map = map;
        first = map.keySet().iterator();
        if (first.hasNext()) {
            value1 = first.next();
            second = map.get(value1).keySet().iterator();
        }
    }

    @Override
    public boolean hasNext() {
        if (second != null && second.hasNext()) {
            return true;
        }
        return first.hasNext();
    }

    @Override
    public Trio<K, V> next() {
        if (!second.hasNext()) {
            value1 = first.next();
            second = map.get(value1).keySet().iterator();
        }
        K value2 = second.next();
        return new Trio<>(value1, value2, map.get(value1).get(value2));
    }
}
