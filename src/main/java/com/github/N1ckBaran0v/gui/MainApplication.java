package com.github.N1ckBaran0v.gui;

import javax.swing.*;
import java.awt.*;

public class MainApplication implements Runnable {
    @Override
    public void run() {
        var frame = new JFrame("Курсовая работа");
        frame.setMinimumSize(new Dimension(600, 400));
        frame.setBounds(300, 200, 600, 400);
        var panel = new CanvasPanel();
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
