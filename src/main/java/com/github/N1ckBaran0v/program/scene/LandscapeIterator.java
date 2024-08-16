package com.github.N1ckBaran0v.program.scene;

import com.github.N1ckBaran0v.program.containers.Trio;

import java.util.Iterator;

class LandscapeIterator implements Iterator<SceneObject> {
    private final Iterator<Trio<Long, Chunk>> mapIterator;

    public LandscapeIterator(Iterator<Trio<Long, Chunk>> mapIterator) {
        this.mapIterator = mapIterator;
    }

    @Override
    public boolean hasNext() {
        return mapIterator != null && mapIterator.hasNext();
    }

    @Override
    public SceneObject next() {
        return mapIterator.next().third;
    }
}
