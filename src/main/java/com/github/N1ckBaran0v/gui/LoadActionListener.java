package com.github.N1ckBaran0v.gui;

import com.github.N1ckBaran0v.program.Facade;
import com.github.N1ckBaran0v.program.LoadLandscapeCommand;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class LoadActionListener implements ActionListener {
    private static final Path PATH = Path.of("saves");

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            var chooser = new JFileChooser();
            chooser.setDialogTitle("Загрузка параметров");
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            if (!Files.exists(PATH)) {
                Files.createDirectory(PATH);
            }
            chooser.setCurrentDirectory(PATH.toFile());
            chooser.setFileFilter(new FileNameExtensionFilter("JSON files", "json"));
            var result = chooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    Facade.execute(new LoadLandscapeCommand(chooser.getSelectedFile().getPath()));
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, "Ошибка при загрузке параметров", "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Ошибка при создании папки", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }
}
