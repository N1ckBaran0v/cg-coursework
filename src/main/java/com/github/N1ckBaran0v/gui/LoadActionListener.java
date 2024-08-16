package com.github.N1ckBaran0v.gui;

import com.github.N1ckBaran0v.program.Facade;
import com.github.N1ckBaran0v.program.LoadLandscapeCommand;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

class LoadActionListener implements ActionListener {
    private final String PATH = "src" + File.separator + "main" + File.separator + "resources";

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        var chooser = new JFileChooser();
        chooser.setDialogTitle("Загрузка параметров");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setCurrentDirectory(new File(PATH));
        var result = chooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                Facade.execute(new LoadLandscapeCommand(chooser.getSelectedFile().getPath()));
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, "Ошибка при загрузке параметров", "Ошибка",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
