package com.github.N1ckBaran0v.gui;

import com.github.N1ckBaran0v.program.Facade;
import com.github.N1ckBaran0v.program.SaveLandscapeCommand;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class SaveActionListener implements ActionListener {
    private static final Path PATH = Path.of("saves");

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            var chooser = new JFileChooser();
            chooser.setDialogTitle("Сохранение параметров");
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            if (!Files.exists(PATH)) {
                Files.createDirectory(PATH);
            }
            chooser.setCurrentDirectory(PATH.toFile());
            chooser.setFileFilter(new FileNameExtensionFilter("JSON files", "json"));
            var result = chooser.showSaveDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                var flag = chooser.getSelectedFile().exists();
                try {
                    Facade.execute(new SaveLandscapeCommand(chooser.getSelectedFile().getPath()));
                    flag = true;
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, "Ошибка при работе с файлом", "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                    flag = false;
                }
                if (!flag) {
                    chooser.getSelectedFile().delete();
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Ошибка при создании папки", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }

    }
}
