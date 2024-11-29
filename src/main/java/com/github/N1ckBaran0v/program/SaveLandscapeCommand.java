package com.github.N1ckBaran0v.program;

import com.google.gson.Gson;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Files;
import java.nio.file.Path;

public class SaveLandscapeCommand implements Command {
    private final String path;

    public SaveLandscapeCommand(String path) {
        this.path = path;
    }

    @Override
    public void execute(@NotNull Context context) {
        if (!context.getLandscape().iterator().hasNext()) {
            throw new NullPointerException();
        }
        var gson = new Gson();
        try (var writer = Files.newBufferedWriter(Path.of(path))) {
            writer.write(gson.toJson(context.getLandscape()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
