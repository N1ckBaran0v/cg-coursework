package com.github.N1ckBaran0v.gui;

import com.github.N1ckBaran0v.program.Facade;
import com.github.N1ckBaran0v.program.MoveCommand;
import com.github.N1ckBaran0v.program.RotateCommand;

class CameraMover extends Thread {
    private final String cameraName;
    private final CanvasPanel panel;
    private boolean goUp, goDown, goForward, goBack, goLeft, goRight;
    private boolean lookUp, lookDown, lookLeft, lookRight;
    private static final double STEP = 8;
    private static final double DEGREES = 1;

    public CameraMover(String cameraName, CanvasPanel panel) {
        this.cameraName = cameraName;
        this.panel = panel;
    }

    @Override
    public void run() {
        while (true) {
            var counter = 0;
            if (lookUp && !lookDown) {
                Facade.execute(new RotateCommand(cameraName, 0, -DEGREES, 0));
                ++counter;
            } else if (lookDown && !lookUp) {
                Facade.execute(new RotateCommand(cameraName, 0, DEGREES, 0));
                ++counter;
            }
            if (lookLeft && !lookRight) {
                Facade.execute(new RotateCommand(cameraName, 0, 0, DEGREES));
                ++counter;
            } else if (lookRight && !lookLeft) {
                Facade.execute(new RotateCommand(cameraName, 0, 0, -DEGREES));
                ++counter;
            }
            if (goUp && !goDown) {
                Facade.execute(new MoveCommand(cameraName, 0, STEP, 0));
                ++counter;
            } else if (goDown && !goUp) {
                Facade.execute(new MoveCommand(cameraName, 0, -STEP, 0));
                ++counter;
            }
            if (goForward && !goBack) {
                Facade.execute(new MoveCommand(cameraName, 0, 0, -STEP));
                ++counter;
            } else if (goBack && !goForward) {
                Facade.execute(new MoveCommand(cameraName, 0, 0, STEP));
                ++counter;
            }
            if (goLeft && !goRight) {
                Facade.execute(new MoveCommand(cameraName, -STEP, 0, 0));
                ++counter;
            } else if (goRight && !goLeft) {
                Facade.execute(new MoveCommand(cameraName, STEP, 0, 0));
                ++counter;
            }
            if (counter > 0) {
                panel.paint(panel.getGraphics());
            } else {
                try {
                    sleep(1000);
                } catch (InterruptedException ignored) {
                }
            }
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
