package com.github.N1ckBaran0v.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PreGenerateActionListener implements ActionListener {
    private final GenerateActionListener generateActionListener;
    private final JComboBox<String> box;
    private final JTextField sideSize, squareSize, step, defaultValue;

    public PreGenerateActionListener(GenerateActionListener generateActionListener, JComboBox<String> box,
                                     List<JTextField> entries) {
        this.generateActionListener = generateActionListener;
        this.box = box;
        this.sideSize = entries.get(0);
        this.squareSize = entries.get(1);
        this.step = entries.get(2);
        this.defaultValue = entries.get(3);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            generateActionListener.setParams(
                    Integer.parseInt(sideSize.getText()),
                    Integer.parseInt(squareSize.getText()),
                    Integer.parseInt(step.getText()),
                    Integer.parseInt(defaultValue.getText())
            );
            box.removeAllItems();
            var sideSizeValue = generateActionListener.getSideSize();
            var squareSizeValue = generateActionListener.getSquareSize();
            for (var i = 0; i <= sideSizeValue; i += squareSizeValue) {
                for (var j = 0; j <= sideSizeValue; j += squareSizeValue) {
                    box.addItem(String.format("%d %d", i, j));
                }
            }
            box.setSelectedIndex(0);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Неправильный формат параметров генерации ландшафта", "Ошибка",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
