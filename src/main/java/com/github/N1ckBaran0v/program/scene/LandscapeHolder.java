package com.github.N1ckBaran0v.program.scene;

public class LandscapeHolder {
    private static Landscape landscape = new Landscape();
    private static Thread thread;
    private static final Generator generator = new Generator();

    private LandscapeHolder() {
    }

    public static Landscape getInstance() {
        return landscape;
    }

    public static void setParams(long seed, double minHeight, double maxHeight, long step, long maxChunks) {
        var gen = new GenerateThread(generator, seed, minHeight, maxHeight, landscape.SIDE_SIZE, step, maxChunks,
                landscape);
        landscape.maxChunks = maxChunks;
        if (thread != null) {
            thread.interrupt();
        }
        gen.start();
    }
}
