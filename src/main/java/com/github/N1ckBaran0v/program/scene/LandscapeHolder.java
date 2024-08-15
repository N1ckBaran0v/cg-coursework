package com.github.N1ckBaran0v.program.scene;

import com.github.N1ckBaran0v.program.Facade;
import com.github.N1ckBaran0v.program.GenerateLandscapeCommand;
import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class LandscapeHolder {
    private static final Landscape landscape = new Landscape();
    private static Thread thread;
    private static final Generator generator = new Generator();

    private LandscapeHolder() {
    }

    public static Landscape getInstance() {
        return landscape;
    }

    public static void setParams(long seed, double minHeight, double maxHeight, long sideSize, long step,
                                 long maxChunks) {
        if (sideSize % step != 0) {
            throw new IllegalArgumentException("sideSize % step must be 0, not " + (sideSize % step));
        }
        landscape.setSeed(seed);
        landscape.setMinHeight(minHeight);
        landscape.setMaxHeight(maxHeight);
        landscape.setSideSize(sideSize);
        landscape.setStep(step);
        landscape.setMaxChunks(maxChunks);
        generator.setParams(landscape);
        var gen = new GenerateThread(generator, landscape);
        landscape.setChunks(null);
        if (thread != null) {
            thread.interrupt();
        }
        thread = gen;
        gen.start();
    }

    public static void setThread(Thread thread) {
        LandscapeHolder.thread = thread;
    }

    public static Generator getGenerator() {
        return generator;
    }

    public static void save(String filename) {
        var gson = new Gson();
        var data = gson.toJson(landscape);
        try (var writer = Files.newBufferedWriter(Path.of(filename))) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void load(String filename) {
        var gson = new Gson();
        try (var reader = Files.newBufferedReader(Path.of(filename))) {
            var twin = gson.fromJson(reader.readLine(), Landscape.class);
            var seed = twin.getSeed();
            var minHeight = twin.getMinHeight();
            var maxHeight = twin.getMaxHeight();
            var sideSize = twin.getSideSize();
            var step = twin.getStep();
            var maxChunks = twin.getMaxChunks();
            Facade.execute(new GenerateLandscapeCommand(seed, minHeight, maxHeight, sideSize, step, maxChunks));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
