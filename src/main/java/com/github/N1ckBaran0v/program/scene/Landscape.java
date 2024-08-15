package com.github.N1ckBaran0v.program.scene;

import com.github.N1ckBaran0v.program.containers.Map2D;

import java.util.Iterator;

public class Landscape extends Composite {
    private Map2D<Long, Chunk> chunks;
    public static final long SIDE_SIZE = 2048;
    public long maxChunks = 1;

    @Override
    public Iterator<SceneObject> iterator() {
        if (chunks == null) {
            return new LandscapeIterator(null);
        }
        return new LandscapeIterator(chunks.iterator());
    }

    public Map2D<Long, Chunk> getChunks() {
        return chunks;
    }

    public void setChunks(Map2D<Long, Chunk> chunks) {
        this.chunks = chunks;
    }
}
