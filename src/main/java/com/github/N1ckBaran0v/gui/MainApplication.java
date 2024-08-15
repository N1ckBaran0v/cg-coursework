package com.github.N1ckBaran0v.gui;

import com.github.N1ckBaran0v.program.*;
import com.github.N1ckBaran0v.swingAdapters.SwingFactory;

import javax.swing.*;
import java.awt.*;

public class MainApplication implements Runnable {
    @Override
    public void run() {
        var frame = new JFrame("Тест");
        frame.setMinimumSize(new Dimension(600, 400));
        frame.setBounds(300, 200, 600, 400);
        var factory = new SwingFactory();
        Facade.setDrawFactory(factory);
        var panel = new CanvasPanel(factory);
        frame.setContentPane(panel);
        Facade.execute(new LoadLandscapeCommand("src/main/resources/bebra.json"));
        Facade.execute(new AddObjectCommand("NotASun", "FarLight"));
        Facade.execute(new RotateCommand("NotASun", 45, 0, 45));
        frame.addKeyListener(new CameraKeyListener(panel.getMover()));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
