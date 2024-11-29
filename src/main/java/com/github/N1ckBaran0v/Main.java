package com.github.N1ckBaran0v;

import com.github.N1ckBaran0v.gui.MainApplication;
import javax.swing.*;

public final class Main {
    public static void main(String[] args) {
        if (args.length == 1 && args[0].equals("-b")) {
            Benchmark.benchmark();
        } else {
            SwingUtilities.invokeLater(new MainApplication());
        }
    }
}