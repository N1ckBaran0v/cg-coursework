package com.github.N1ckBaran0v.gui;

import com.github.N1ckBaran0v.program.AddObjectCommand;
import com.github.N1ckBaran0v.program.Facade;
import com.github.N1ckBaran0v.program.RotateCommand;
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
        Facade.execute(new AddObjectCommand("Sphere 1", "Sphere"));
//        Facade.execute(new RotateCommand("Sphere 1", 50, 0, 0));
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
