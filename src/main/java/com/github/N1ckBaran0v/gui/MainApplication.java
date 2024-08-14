package com.github.N1ckBaran0v.gui;

import com.github.N1ckBaran0v.program.Facade;
import com.github.N1ckBaran0v.program.GenerateLandscapeCommand;
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
        Facade.execute(new GenerateLandscapeCommand(69, 0, 1024, 128, 4));
        frame.addKeyListener(new CameraKeyListener(panel.getMover()));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
