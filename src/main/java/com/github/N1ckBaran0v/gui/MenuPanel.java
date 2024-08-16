package com.github.N1ckBaran0v.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class MenuPanel extends JPanel {
    private static final int WIDTH = 260;
    private static final int HEIGHT = 20;
    private static final int OFFSET = 10;
    private static final Font STANDART_FONT = new Font(Font.MONOSPACED, Font.PLAIN, 14);
    private static final Font TITLE_FONT = new Font(Font.MONOSPACED, Font.BOLD, 20);

    public MenuPanel() {
        setBackground(Color.BLACK);
        setLayout(null);
        add(addLabel("Параметры генерации", OFFSET, OFFSET, WIDTH, true));
        var labels = new String[]{"Ключ генерации", "Минимальная высота", "Максимальная высота"};
        var entryDefaults = new String[]{"", "0", "1024"};
        var entries = new ArrayList<JTextField>();
        for (var i = 0; i < 3; ++i) {
            add(addLabel(labels[i], OFFSET, OFFSET + (2 * i + 1) * (OFFSET + HEIGHT), WIDTH, false));
            var entry = addTextField(entryDefaults[i], OFFSET, OFFSET + (2 * (i + 1)) * (OFFSET + HEIGHT), WIDTH);
            add(entry);
            entries.add(entry);
        }
        labels = new String[]{"Сторона квадрата", "Шаг отрисовки", "Дальность прорисовки"};
        var ranges = new long[][]{{1024, 2048, 4096}, {32, 64, 128, 256, 512, 1024}, {1, 2, 3, 4, 5, 6, 7, 8}};
        var startIndexes = new int[]{1, 2, 3};
        var paramLabels = new ArrayList<JLabel>();
        for (var i = 0; i < 3; ++i) {
            paramLabels.add(addRange(labels[i], OFFSET + (OFFSET + HEIGHT) * (7 + 2 * i), ranges[i], startIndexes[i]));
        }
        add(addButton("Сгенерировать", OFFSET, OFFSET + (OFFSET + HEIGHT) * 13, WIDTH,
                new GenerateActionListener(entries, paramLabels)));
        add(addButton("Загрузить из файла", OFFSET, OFFSET + (OFFSET + HEIGHT) * 14, WIDTH, new LoadActionListener()));
        add(addButton("Сохранить в файл", OFFSET, OFFSET + (OFFSET + HEIGHT) * 15, WIDTH, new SaveActionListener()));
        add(addLabel("Источник света", OFFSET, OFFSET + (OFFSET + HEIGHT) * 16, WIDTH, true));
        var angles = addAngles(OFFSET + (OFFSET + HEIGHT) * 17);
        add(addButton("Применить", OFFSET, OFFSET + (OFFSET + HEIGHT) * 19, WIDTH, new LightActionListener(angles)));
    }

    private JLabel addLabel(String text, int x, int y, int width, boolean isTitle) {
        var label = new JLabel(text, JLabel.CENTER);
        label.setBackground(Color.BLACK);
        label.setForeground(Color.WHITE);
        label.setFont(isTitle ? TITLE_FONT : STANDART_FONT);
        label.setBounds(x, y, width, HEIGHT);
        return label;
    }

    private JTextField addTextField(String text, int x, int y, int width) {
        var field = new JTextField(text);
        field.setFont(STANDART_FONT);
        field.setBounds(x, y, width, HEIGHT);
        return field;
    }

    private JLabel addRange(String text, int y, long[] rangeValues, int index) {
        add(addLabel(text, OFFSET, y, WIDTH, false));
        y += OFFSET + HEIGHT;
        var label = addLabel(rangeValues[index] + "", 2 * OFFSET + 40, y, WIDTH - 2 * (OFFSET + 40), false);
        add(label);
        var range = new Range(label, rangeValues, index);
        add(addButton("<", OFFSET, y, 40, new RangeActionListener(range, false)));
        add(addButton(">", OFFSET + WIDTH - 40, y, 40, new RangeActionListener(range, true)));
        return label;
    }

    private JButton addButton(String text, int x, int y, int width, ActionListener action) {
        var button = new JButton(text);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        button.setFont(STANDART_FONT);
        button.setBounds(x, y, width, HEIGHT);
        button.addActionListener(action);
        return button;
    }

    private List<JTextField> addAngles(int y) {
        var list = new ArrayList<JTextField>();
        var width = (WIDTH - OFFSET) >> 1;
        add(addLabel("Угол по оси OX", OFFSET, y, width, false));
        add(addLabel("Угол по оси OZ", OFFSET * 2 + width, y, width, false));
        y += OFFSET + HEIGHT;
        var field1 = addTextField("0", OFFSET, y, width);
        var field2 = addTextField("0", OFFSET * 2 + width, y, width);
        add(field1);
        add(field2);
        list.add(field1);
        list.add(field2);
        return list;
    }
}
