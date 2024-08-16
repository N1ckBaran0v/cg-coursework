package com.github.N1ckBaran0v.program.scene;

import com.github.N1ckBaran0v.program.containers.Map2D;

import java.util.Iterator;

public class Landscape extends Composite {
    private transient Map2D<Long, Chunk> chunks;
    private long seed = 0;
    private double minHeight = 0, maxHeight = 0;
    private long sideSize = 1000, step = 100, maxChunks = 1;

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

    public long getSeed() {
        return seed;
    }

    public void setSeed(long seed) {
        this.seed = seed;
    }

    public double getMinHeight() {
        return minHeight;
    }

    public void setMinHeight(double minHeight) {
        this.minHeight = minHeight;
    }

    public double getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(double maxHeight) {
        this.maxHeight = maxHeight;
    }

    public long getSideSize() {
        return sideSize;
    }

    public void setSideSize(long sideSize) {
        this.sideSize = sideSize;
    }

    public long getStep() {
        return step;
    }

    public void setStep(long step) {
        this.step = step;
    }

    public long getMaxChunks() {
        return maxChunks;
    }

    public void setMaxChunks(long maxChunks) {
        this.maxChunks = maxChunks;
    }
}
