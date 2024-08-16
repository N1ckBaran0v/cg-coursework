package com.github.N1ckBaran0v.gui;

import com.github.N1ckBaran0v.program.Facade;
import com.github.N1ckBaran0v.program.MoveCommand;
import com.github.N1ckBaran0v.program.RotateCommand;

class CameraMover extends Thread {
    private final String cameraName;
    private final CanvasPanel panel;
    private boolean goUp, goDown, goForward, goBack, goLeft, goRight;
    private boolean lookUp, lookDown, lookLeft, lookRight;
    private static final double STEP = 16;
    private static final double DEGREES = 1;

    public CameraMover(String cameraName, CanvasPanel panel) {
        this.cameraName = cameraName;
        this.panel = panel;
    }

    @Override
    public void run() {
        while (true) {
            if (lookUp && !lookDown) {
                Facade.execute(new RotateCommand(cameraName, 0, DEGREES, 0));
            } else if (lookDown && !lookUp) {
                Facade.execute(new RotateCommand(cameraName, 0, -DEGREES, 0));
            }
            if (lookLeft && !lookRight) {
                Facade.execute(new RotateCommand(cameraName, 0, 0, DEGREES));
            } else if (lookRight && !lookLeft) {
                Facade.execute(new RotateCommand(cameraName, 0, 0, -DEGREES));
            }
            if (goUp && !goDown) {
                Facade.execute(new MoveCommand(cameraName, 0, STEP, 0));
            } else if (goDown && !goUp) {
                Facade.execute(new MoveCommand(cameraName, 0, -STEP, 0));
            }
            if (goForward && !goBack) {
                Facade.execute(new MoveCommand(cameraName, 0, 0, STEP));
            } else if (goBack && !goForward) {
                Facade.execute(new MoveCommand(cameraName, 0, 0, -STEP));
            }
            if (goLeft && !goRight) {
                Facade.execute(new MoveCommand(cameraName, STEP, 0, 0));
            } else if (goRight && !goLeft) {
                Facade.execute(new MoveCommand(cameraName, -STEP, 0, 0));
            }
            panel.paint(panel.getGraphics());
        }
    }

    public void setGoUp(boolean goUp) {
        this.goUp = goUp;
    }

    public void setGoDown(boolean goDown) {
        this.goDown = goDown;
    }

    public void setGoForward(boolean goForward) {
        this.goForward = goForward;
    }

    public void setGoBack(boolean goBack) {
        this.goBack = goBack;
    }

    public void setGoLeft(boolean goLeft) {
        this.goLeft = goLeft;
    }

    public void setGoRight(boolean goRight) {
        this.goRight = goRight;
    }

    public void setLookUp(boolean lookUp) {
        this.lookUp = lookUp;
    }

    public void setLookDown(boolean lookDown) {
        this.lookDown = lookDown;
    }

    public void setLookLeft(boolean lookLeft) {
        this.lookLeft = lookLeft;
    }

    public void setLookRight(boolean lookRight) {
        this.lookRight = lookRight;
    }
}
