package com.github.N1ckBaran0v.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class SaveHeightActionListener implements ActionListener {
    private final GenerateActionListener generateActionListener;
    private final JComboBox<String> comboBox;
    private final JTextField textField;

    public SaveHeightActionListener(GenerateActionListener generateActionListener, JComboBox<String> comboBox, JTextField textField) {
        this.generateActionListener = generateActionListener;
        this.comboBox = comboBox;
        this.textField = textField;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            var value = Double.parseDouble(textField.getText());
            var parts = Objects.requireNonNull(comboBox.getSelectedItem()).toString().split(" ");
            var x = Integer.parseInt(parts[0]);
            var y = Integer.parseInt(parts[1]);
            generateActionListener.set(x, y, value);
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, "Неправильный формат числа", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }
}
