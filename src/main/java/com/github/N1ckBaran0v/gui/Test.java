package com.github.N1ckBaran0v.gui;

import com.github.N1ckBaran0v.program.Facade;
import com.github.N1ckBaran0v.program.RotateCommand;

import javax.swing.*;

public class Test extends Thread {
    private final JPanel panel;
    private final RotateCommand rotate = new RotateCommand("Sphere 1", 1, 0, 0);

    public Test(JPanel panel) {
        this.panel = panel;
    }

    @Override
    public void run() {
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        while (true) {
            Facade.execute(rotate);
            panel.paint(panel.getGraphics());
            try {
                sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
