package com.github.N1ckBaran0v.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class MenuPanel extends JPanel {
    private static final int WIDTH = 340;
    private static final int HEIGHT = 20;
    private static final int OFFSET = 10;
    private static final int STEP = HEIGHT + OFFSET;
    private static final int HALF_WIDTH = (WIDTH - OFFSET) >> 1;
    private static final int OFFSET_2 = (OFFSET << 1) + HALF_WIDTH;
    private static final Font STANDART_FONT = new Font(Font.MONOSPACED, Font.PLAIN, 12);
    private static final Font TITLE_FONT = new Font(Font.MONOSPACED, Font.BOLD, 20);
    private final GenerateActionListener genListener = new GenerateActionListener();

    public MenuPanel() {
        setBackground(Color.BLACK);
        setLayout(null);
        add(addLabel("Параметры генерации", OFFSET, OFFSET, WIDTH, true));
        var labels = new String[]{
                "Размер стороны",
                "Шаг задания высот",
                "Шаг интерполяции",
                "Значение по умолчанию"
        };
        var entryDefaults = new String[]{"10000", "2000", "500", "0"};
        var entries = new ArrayList<JTextField>();
        for (var i = 0; i < labels.length; ++i) {
            var offset = OFFSET + (i % 2) * (OFFSET + HALF_WIDTH);
            add(addLabel(labels[i], offset, OFFSET + (2 * (i / 2) + 1) * STEP, HALF_WIDTH, false));
            var entry = addTextField(entryDefaults[i], offset, OFFSET + (2 * ((i / 2) + 1)) * STEP, HALF_WIDTH);
            add(entry);
            entries.add(entry);
        }
        add(addLabel("Значение высоты", OFFSET_2, OFFSET + 6 * STEP, HALF_WIDTH, false));
        var entry = addTextField("", OFFSET_2, OFFSET + 7 * STEP, HALF_WIDTH);
        var box = addComboBox("Выбор точки", OFFSET + 6 * STEP, entry);
        var preGen = new PreGenerateActionListener(genListener, box, entries);
        add(addButton("Сгенерировать точки", OFFSET, OFFSET + STEP * 5, WIDTH, preGen));
        add(box);
        add(entry);
        add(addButton("Обновить значение в точке", OFFSET, OFFSET + STEP * 8, WIDTH,
                new SaveHeightActionListener(genListener, box, entry)));
        add(addButton("Сгенерировать ландшафт", OFFSET, OFFSET + STEP * 9, WIDTH, genListener));
        preGen.actionPerformed(null);
        genListener.actionPerformed(null);
        add(addButton("Загрузить", OFFSET, OFFSET + STEP * 10, HALF_WIDTH, new LoadActionListener()));
        add(addButton("Сохранить", OFFSET_2, OFFSET + STEP * 10, HALF_WIDTH, new SaveActionListener()));
        add(addLabel("Источник света", OFFSET, OFFSET + STEP * 11, WIDTH, true));
        var angles = addAngles(OFFSET + STEP * 12);
        add(addButton("Применить", OFFSET, OFFSET + STEP * 14, WIDTH, new LightActionListener(angles)));
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
        add(addLabel("Угол по оси OX", OFFSET, y, HALF_WIDTH, false));
        add(addLabel("Угол по оси OZ", OFFSET * 2 + HALF_WIDTH, y, HALF_WIDTH, false));
        y += OFFSET + HEIGHT;
        var field1 = addTextField("0", OFFSET, y, HALF_WIDTH);
        var field2 = addTextField("0", OFFSET * 2 + HALF_WIDTH, y, HALF_WIDTH);
        add(field1);
        add(field2);
        list.add(field1);
        list.add(field2);
        return list;
    }

    private JComboBox<String> addComboBox(String text, int y, JTextField field) {
    add(addLabel(text, OFFSET, y, HALF_WIDTH, false));
        y += OFFSET + HEIGHT;
        var box = new JComboBox<String>() {
            @Override
            protected void selectedItemChanged() {
                super.selectedItemChanged();
                if (getSelectedItem() != null) {
                    try {
                        var items = ((String) getSelectedItem()).split(" ");
                        var x = Integer.parseInt(items[0]);
                        var y = Integer.parseInt(items[1]);
                        field.setText(String.valueOf(genListener.get(x, y)));
                    } catch (Exception ignored) {
                    }
                }
            }
        };
        box.setBackground(Color.BLACK);
        box.setForeground(Color.WHITE);
        box.setFont(STANDART_FONT);
        box.setBounds(OFFSET, y, HALF_WIDTH, HEIGHT);
        return box;
    }
}
