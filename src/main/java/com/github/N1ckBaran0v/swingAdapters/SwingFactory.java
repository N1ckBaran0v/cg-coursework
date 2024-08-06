package com.github.N1ckBaran0v.swingAdapters;

import com.github.N1ckBaran0v.program.guiAdapters.AbstractDrawFactory;
import com.github.N1ckBaran0v.program.guiAdapters.AbstractGraphics;
import com.github.N1ckBaran0v.program.guiAdapters.AbstractImage;
import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class SwingFactory implements AbstractDrawFactory {
    private final Map<String, JPanel> cameras = new HashMap<>();
    private final Map<JPanel, AbstractGraphics> graphics = new HashMap<>();

    public void register(String camera, JPanel panel) {
        cameras.put(camera, panel);
    }

    @Override
    public AbstractImage getImage(String cameraName) {
        var panel = cameras.get(cameraName);
        return new SwingImage(panel.getWidth(), panel.getHeight());
    }

    @Override
    public AbstractGraphics getGraphics(String cameraName) {
        var panel = cameras.get(cameraName);
        if (!graphics.containsKey(panel)) {
            graphics.put(panel, new SwingGraphics(panel.getGraphics(), panel));
        }
        return graphics.get(panel);
    }
}
