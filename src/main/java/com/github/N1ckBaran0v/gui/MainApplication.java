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
        frame.setMinimumSize(new Dimension(960, 540));
        frame.setBounds(300, 200, 960, 540);
        var factory = new SwingFactory();
        Facade.setDrawFactory(factory);
        var canvas = new CanvasPanel(factory);
        var splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new MenuPanel(), canvas);
        splitPane.setEnabled(false);
        splitPane.setDividerLocation(360);
        frame.setContentPane(splitPane);
        addMenuBar(frame);
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
        var info = new JMenuItem("О программе");
        info.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                var sb = new StringBuilder();
                sb.append("Курсовая работа по теме \"Генерация ландшафта\".");
                sb.append("\nПрограмма генерирует ландшафт по заданным параметрам.");
                sb.append("\n1. Размер стороны - натуральное число.");
                sb.append("\n2. Шаг задания точек - натуральное число.");
                sb.append("\n3. Шаг интерполяции - натуральное число.");
                sb.append("\n4. Значение в точке - вещественное число.");
                sb.append("\nРазмер стороны квадрата не должен быть меньше шага задания точек,");
                sb.append("\nпричём размер стороны квадрата должен делиться на шаг задания точек.");
                sb.append("\nАналогично для шага задания точек и шага интерполяции.");
                sb.append("\nТакже в программе присутствуют источник света и камера.");
                sb.append("\nПро управление камерой можно прочитать в соответствующем разделе.");
                sb.append("\nВектор направления источника света задается двумя углами.");
                JOptionPane.showMessageDialog(null, sb.toString(),
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
        var author = new JMenuItem("Об авторе");
        author.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null, "Баранов Николай Алексеевич, группа ИУ7-51Б", "Автор",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
        menu.add(author);
        var exit = new JMenuItem("Выход");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        menu.add(exit);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);
    }
}
