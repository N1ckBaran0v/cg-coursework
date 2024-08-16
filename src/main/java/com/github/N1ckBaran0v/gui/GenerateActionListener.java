package com.github.N1ckBaran0v.gui;

import com.github.N1ckBaran0v.program.Facade;
import com.github.N1ckBaran0v.program.GenerateLandscapeCommand;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;

class GenerateActionListener implements ActionListener {
    private final List<JTextField> fields;
    private final List<JLabel> labels;
    private final Random gen = new Random();

    public GenerateActionListener(List<JTextField> fields, List<JLabel> labels) {
        this.fields = fields;
        this.labels = labels;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            var seedText = fields.get(0).getText();
            var seed = 0l;
            if ("".equals(seedText)) {
                seed = gen.nextLong();
            } else {
                seed = Long.parseLong(seedText);
            }
            var minHeight = Double.parseDouble(fields.get(1).getText());
            var maxHeight = Double.parseDouble(fields.get(2).getText());
            var sideSize = Long.parseLong(labels.get(0).getText());
            var step = Long.parseLong(labels.get(1).getText());
            var maxChunks = Long.parseLong(labels.get(2).getText());
            Facade.execute(new GenerateLandscapeCommand(seed, minHeight, maxHeight, sideSize, step, maxChunks));
        } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(null, "Ошибка при чтении данных", "Ошибка", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException exception) {
            JOptionPane.showMessageDialog(null, "Нижняя граница высоты не может быть выше верхней", "Ошибка",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
