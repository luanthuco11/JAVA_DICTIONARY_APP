package ui;

import java.awt.BorderLayout;
import java.util.Collections;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import models.*;
public class HistoryUI {
    public static  JPanel createAndShowGUI(SlangWordManager data)
    {
        JPanel results = new JPanel(new BorderLayout());
        Collections.reverse(data.histories);
        JList<String> list = new JList<>(data.histories.toArray(new String[0]));
        results.add(new JLabel("Histories"), BorderLayout.NORTH);
        results.add(new JScrollPane(list), BorderLayout.CENTER);

        Collections.reverse(data.histories);

        return results;
    }
}
