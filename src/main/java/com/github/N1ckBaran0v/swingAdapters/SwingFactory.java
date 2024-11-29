package com.github.N1ckBaran0v.swingAdapters;

import com.github.N1ckBaran0v.program.guiAdapters.AbstractDrawFactory;
import com.github.N1ckBaran0v.program.guiAdapters.AbstractGraphics;
import com.github.N1ckBaran0v.program.guiAdapters.AbstractImage;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class SwingFactory implements AbstractDrawFactory {
    private final Map<String, JPanel> cameras = new HashMap<>();
    private final Map<JPanel, Graphics> graphics = new HashMap<>();

    public void register(@NotNull String camera, @NotNull JPanel panel) {
        cameras.put(camera, panel);
    }

    @Override
    public AbstractImage getImage(@NotNull String cameraName) {
        var panel = cameras.get(cameraName);
        return new SwingImage(panel.getWidth(), panel.getHeight());
    }

    public void setGraphics(@NotNull JPanel panel, Graphics g) {
        graphics.put(panel, g);
    }

    @Override
    public AbstractGraphics getGraphics(@NotNull String cameraName) {
        var panel = cameras.get(cameraName);
        return new SwingGraphics(graphics.get(panel), panel);
    }
}
