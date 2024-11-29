package com.github.N1ckBaran0v.program;

import com.github.N1ckBaran0v.program.scene.Landscape;
import com.google.gson.Gson;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Files;
import java.nio.file.Path;

public class LoadLandscapeCommand implements Command {
    private final String path;

    public LoadLandscapeCommand(String path) {
        this.path = path;
    }

    @Override
    public void execute(@NotNull Context context) {
        var gson = new Gson();
        try (var reader = Files.newBufferedReader(Path.of(path))) {
            var twin = gson.fromJson(reader.readLine(), Landscape.class);
            var heights = twin.getInputHeightsMap();
            var sideSize = twin.getSideSize();
            var squareSize = twin.getSquareSize();
            var step = twin.getStep();
            Facade.execute(new GenerateLandscapeCommand(heights, sideSize, squareSize, step));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
