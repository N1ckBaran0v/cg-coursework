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
        switch (keyEvent.getKeyChar()) {
            case 'w', 'ц' -> mover.setGoForward(true);
            case 's', 'ы' -> mover.setGoBack(true);
            case 'a', 'ф' -> mover.setGoLeft(true);
            case 'd', 'в' -> mover.setGoRight(true);
        }
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case 32 -> mover.setGoUp(true);
            case 16 -> mover.setGoDown(true);
            case 38 -> mover.setLookUp(true);
            case 40 -> mover.setLookDown(true);
            case 37 -> mover.setLookLeft(true);
            case 39 -> mover.setLookRight(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
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
