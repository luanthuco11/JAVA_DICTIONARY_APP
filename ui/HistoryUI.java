package ui;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import models.*;
public class HistoryUI {
    public static  JPanel createAndShowGUI(Data data)
    {
        JPanel results = new JPanel();
        JList<String> list = new JList<>(data.histories.toArray(new String[0]));
        results.add(new JScrollPane(list));
        return results;
    }
}
