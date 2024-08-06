package com.github.N1ckBaran0v.swingAdapters;

import com.github.N1ckBaran0v.program.guiAdapters.AbstractDrawFactory;
import com.github.N1ckBaran0v.program.guiAdapters.AbstractGraphics;
import com.github.N1ckBaran0v.program.guiAdapters.AbstractImage;
import javax.swing.*;

public class SwingFactory implements AbstractDrawFactory {
    private final JPanel panel;
    private AbstractGraphics graphics;

    public SwingFactory(JPanel panel) {
        this.panel = panel;
    }

    @Override
    public AbstractImage getImage() {
        return new SwingImage(panel.getWidth(), panel.getHeight());
    }

    @Override
    public AbstractGraphics getGraphics() {
        if (graphics == null) {
            graphics = new SwingGraphics(panel.getGraphics(), panel);
        }
        return graphics;
    }
}
