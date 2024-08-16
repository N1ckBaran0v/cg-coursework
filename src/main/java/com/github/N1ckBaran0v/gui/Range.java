package com.github.N1ckBaran0v.gui;

import javax.swing.*;

class Range {
    private final JLabel label;
    private final long[] range;
    private int index;

    public Range(JLabel label, long[] range, int index) {
        this.label = label;
        this.range = range;
        this.index = index;
    }

    public void up() {
        if (index < range.length - 1) {
            ++index;
            label.setText(range[index] + "");
        }
    }

    public void down() {
        if (index > 0) {
            --index;
            label.setText(range[index] + "");
        }
    }
}
