package ui;
import models.*;
import java.lang.reflect.Array;
import java.util.List;


import javax.swing.*;

import java.awt.*;

public class MainUI {
    static List<String> contentFeatures = List.of("Tìm kiếm theo slang word", 
    "Tìm kiếm theo definition", "Lịch sử đã tìm kiếm theo slang word", 
    "Quản lý slang word", "Reset danh sách slang word", "Random slang word",
    "Đố vui tìm nghĩa của slang word", "Đố vui tìm slang word theo nghĩa");
   




    public static void creatAndShowGUI(Data data)
    {
        JFrame mainFrame = new JFrame("Dictionary App");
        mainFrame.setSize(800, 500);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.add(SearchByDefinitionUI.creatAndShowGUI(), BorderLayout.CENTER);
        // JPanel container = new JPanel(new GridLayout(4,2));
        // mainFrame.add(container);
        // contentFeatures.forEach(item -> container.add(new Button(item)));

        mainFrame.setVisible(true);

    }
}
