package com.github.N1ckBaran0v.gui;

import com.github.N1ckBaran0v.program.AddObjectCommand;
import com.github.N1ckBaran0v.program.DrawCommand;
import com.github.N1ckBaran0v.program.Facade;
import com.github.N1ckBaran0v.program.MoveCommand;
import com.github.N1ckBaran0v.swingAdapters.SwingFactory;

import javax.swing.*;
import java.awt.*;

class CanvasPanel extends JPanel {
    private static int cameras = 0;
    private final SwingFactory factory;
    private final DrawCommand drawCommand;
    private final CameraMover mover;

    public CanvasPanel(SwingFactory drawFactory) {
        factory = drawFactory;
        var name = "Camera " + ++cameras;
        Facade.execute(new AddObjectCommand(name, "Camera"));
        Facade.execute(new MoveCommand(name, 0, 0, 1024));
        drawFactory.register(name, this);
        drawCommand = new DrawCommand(name);
        mover = new CameraMover(name, this);
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
}
