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
        var info = new JMenuItem("О программе");
        info.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                var sb = new StringBuilder();
                sb.append("Курсовая работа по теме \"Генерация ландшафта\".");
                sb.append("\nПрограмма генерирует ландшафт по заданным параметрам.");
                sb.append("\nКлюч генерации - целое число, необходимое для настройки генератора.");
                sb.append("\nОставьте это поле пустым для применения случайного ключа генерации.");
                sb.append("\nМинимальная и максимальная высоты - 2 вещественных числа, задающие диапазон высот ландшафта.");
                sb.append("\nРазмер квадрата - натуральное число, задающее размер стороны квадрата генерации.");
                sb.append("\nШаг отрисовки - натуральное число, задающее размер стороны полигонов.");
                sb.append("\nДальность прорисовки - натуральное число, задающее дальность видимости (в квадратах).");
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
