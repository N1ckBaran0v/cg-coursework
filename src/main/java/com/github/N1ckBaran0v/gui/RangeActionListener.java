package com.github.N1ckBaran0v.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class RangeActionListener implements ActionListener {
    private final Range range;
    private final boolean isUp;

    public RangeActionListener(Range range, boolean isUp) {
        this.range = range;
        this.isUp = isUp;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (isUp) {
            range.up();
        } else {
            range.down();
        }
    }
}
