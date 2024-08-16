package com.github.N1ckBaran0v.gui;

import com.github.N1ckBaran0v.program.*;
import com.github.N1ckBaran0v.swingAdapters.SwingFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainApplication implements Runnable {
    static {
        JFrame.setDefaultLookAndFeelDecorated(true);
        try {
            for (var info : UIManager.getInstalledLookAndFeels()) {
                if ("GTK+".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (Exception ignored) {
        }
    }

    @Override
    public void run() {
        var frame = new JFrame("Генерация ландшафта");
        frame.setMinimumSize(new Dimension(1120, 630));
        frame.setBounds(300, 200, 1120, 630);
        var factory = new SwingFactory();
        Facade.setDrawFactory(factory);
        var canvas = new CanvasPanel(factory);
        var splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new MenuPanel(), canvas);
        splitPane.setEnabled(false);
        splitPane.setDividerLocation(280);
        frame.setContentPane(splitPane);
        addMenuBar(frame);
        Facade.execute(new AddObjectCommand("FarLight", "FarLight"));
        frame.addMouseListener(new CanvasMouseListener(canvas));
        frame.addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                canvas.requestFocusInWindow();
            }
        });
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void addMenuBar(JFrame frame) {
        var menuBar = new JMenuBar();
        var menu = new JMenu("Информация");
        var info = new JMenuItem("Программа");
        info.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null, "Курсовая работа по теме \"Генерация ландшафта\"",
                        "Информация о программе", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        menu.add(info);
        var camera = new JMenuItem("Управление камерой");
        camera.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                var sb = new StringBuilder();
                sb.append("Для управления камерой необходимо навести фокус на холст. ");
                sb.append("\nДля этого нажмите любую кнопку мыши, наведя курсор на холст. ");
                sb.append("\nПри запуске программы фокус автоматически наводится на холст. ");
                sb.append("\nКнопки для управлением камерой: ");
                sb.append("\nw - двигаться вперед;");
                sb.append("\na - двигаться влево;");
                sb.append("\ns - двигаться назад;");
                sb.append("\nd - двигаться вправо;");
                sb.append("\nпробел - двигаться вверх;");
                sb.append("\nshift - двигаться вниз;");
                sb.append("\nстрелка вверх - поднять взгляд камеры;");
                sb.append("\nстрелка вниз - опустить взгляд камеры;");
                sb.append("\nстрелка влево - повернуть взгляд камеры против часовой стрелки;");
                sb.append("\nстрелка вправо - повернуть взгляд камеры по часовой стрелке.");
                JOptionPane.showMessageDialog(null, sb.toString(), "Управление камерой",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
        menu.add(camera);
        var author = new JMenuItem("Автор");
        author.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null, "Баранов Николай Алексеевич", "Автор",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
        menu.add(author);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);
    }
}
