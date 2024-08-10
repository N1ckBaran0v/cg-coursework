package com.github.N1ckBaran0v.gui;

import com.github.N1ckBaran0v.program.Command;
import com.github.N1ckBaran0v.program.Facade;
import com.github.N1ckBaran0v.program.MoveCommand;
import com.github.N1ckBaran0v.program.RotateCommand;

import javax.swing.*;

public class Test extends Thread {
    private final JPanel panel;
    private final Command rotate = new RotateCommand("Camera 1", 0, 0, 1);
    private final Command move = new MoveCommand("Camera 1", -1024 * Math.sin(Math.toRadians(1)) * 2, 0, 0);


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
            Facade.execute(move);
            Facade.execute(rotate);
            panel.paint(panel.getGraphics());
        }
    }
}
