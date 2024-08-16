package com.github.N1ckBaran0v.gui;

import com.github.N1ckBaran0v.program.Facade;
import com.github.N1ckBaran0v.program.RotateCommand;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

class LightActionListener implements ActionListener {
    private final List<JTextField> fields;

    public LightActionListener(List<JTextField> fields) {
        this.fields = fields;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            var ax = Double.parseDouble(fields.get(0).getText());
            var az = Double.parseDouble(fields.get(1).getText());
            Facade.execute(new RotateCommand("FarLight", ax, 0, az));
        } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(null, "Ошибка при чтении данных", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }
}
