package com.github.N1ckBaran0v.gui;

import com.github.N1ckBaran0v.program.Facade;
import com.github.N1ckBaran0v.program.SaveLandscapeCommand;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

class SaveActionListener implements ActionListener {
    private final String PATH = "src" + File.separator + "main" + File.separator + "resources";

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        var chooser = new JFileChooser();
        chooser.setDialogTitle("Сохранение параметров");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setCurrentDirectory(new File(PATH));
        var result = chooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            var flag = chooser.getSelectedFile().exists();
            try {
                Facade.execute(new SaveLandscapeCommand(chooser.getSelectedFile().getPath()));
                flag = true;
            } catch (NullPointerException exception) {
                JOptionPane.showMessageDialog(null, "Ландшафт не сгенерирован", "Ошибка", JOptionPane.ERROR_MESSAGE);
                flag = false;
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, "Ошибка при работе с файлом", "Ошибка", JOptionPane.ERROR_MESSAGE);
                flag = false;
            }
            if (!flag) {
                chooser.getSelectedFile().delete();
            }
        }
    }
}
