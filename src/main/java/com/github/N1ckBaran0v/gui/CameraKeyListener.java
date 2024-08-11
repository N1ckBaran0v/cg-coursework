package com.github.N1ckBaran0v.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class CameraKeyListener implements KeyListener {
    private final CameraMover mover;

    public CameraKeyListener(CameraMover mover) {
        this.mover = mover;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
        System.out.println("Tapped: " + keyEvent.getKeyCode());
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        System.out.println("Pressed: " + keyEvent.getKeyCode());
        switch (keyEvent.getKeyCode()) {
            case 87 -> mover.setGoForward(true);
            case 83 -> mover.setGoBack(true);
            case 65 -> mover.setGoLeft(true);
            case 68 -> mover.setGoRight(true);
            case 32 -> mover.setGoUp(true);
            case 16 -> mover.setGoDown(true);
            case 38 -> mover.setLookUp(true);
            case 40 -> mover.setLookDown(true);
            case 37 -> mover.setLookLeft(true);
            case 39 -> mover.setLookRight(true);
        }
        mover.interrupt();
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        System.out.println("Released " + keyEvent.getKeyCode());
        switch (keyEvent.getKeyCode()) {
            case 87 -> mover.setGoForward(false);
            case 83 -> mover.setGoBack(false);
            case 65 -> mover.setGoLeft(false);
            case 68 -> mover.setGoRight(false);
            case 32 -> mover.setGoUp(false);
            case 16 -> mover.setGoDown(false);
            case 38 -> mover.setLookUp(false);
            case 40 -> mover.setLookDown(false);
            case 37 -> mover.setLookLeft(false);
            case 39 -> mover.setLookRight(false);
        }
    }
}
