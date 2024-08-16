package com.github.N1ckBaran0v.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class CanvasMouseListener implements MouseListener {
    private final CanvasPanel canvasPanel;

    public CanvasMouseListener(CanvasPanel canvasPanel) {
        this.canvasPanel = canvasPanel;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        canvasPanel.requestFocusInWindow();
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
    }
}
