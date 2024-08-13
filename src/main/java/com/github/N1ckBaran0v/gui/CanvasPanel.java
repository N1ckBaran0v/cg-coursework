package com.github.N1ckBaran0v.gui;

import com.github.N1ckBaran0v.program.AddObjectCommand;
import com.github.N1ckBaran0v.program.DrawCommand;
import com.github.N1ckBaran0v.program.Facade;
import com.github.N1ckBaran0v.swingAdapters.SwingFactory;

import javax.swing.*;
import java.awt.*;

class CanvasPanel extends JPanel {
    private static int cameras = 0;
    private final SwingFactory factory;
    private final DrawCommand drawCommand;
    private final CameraMover mover;
    private final String cameraName;

    public CanvasPanel(SwingFactory drawFactory) {
        factory = drawFactory;
        cameraName = "Camera " + ++cameras;
        Facade.execute(new AddObjectCommand(cameraName, "Camera"));
        drawFactory.register(cameraName, this);
        drawCommand = new DrawCommand(cameraName);
        mover = new CameraMover(cameraName, this);
        mover.start();
    }

    @Override
    public void paint(Graphics g) {
        factory.setGraphics(this, g);
        Facade.execute(drawCommand);
    }

    public CameraMover getMover() {
        return mover;
    }

    public String getCameraName() {
        return cameraName;
    }
}
