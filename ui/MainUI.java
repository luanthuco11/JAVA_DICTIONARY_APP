package ui;
import models.*;
import java.lang.reflect.Array;
import java.util.List;


import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Stack;
public class MainUI {
    static List<String> contentFeatures = List.of("Tìm kiếm theo slang word", 
    "Tìm kiếm theo definition", "Lịch sử đã tìm kiếm theo slang word", 
    "Quản lý slang word", "Reset danh sách slang word", "Random slang word",
    "Đố vui tìm nghĩa của slang word", "Đố vui tìm slang word theo nghĩa");
   

    private static JFrame mainFrame;
    private static JPanel container;
    private static Stack<JPanel> nav = new Stack<>();

    private static  void swapPanel(JPanel panel) {
            if (container.getComponentCount() > 0) {
                nav.push((JPanel) container.getComponent(0)); // lưu panel hiện tại
                container.removeAll();
            }
            container.add(panel);
            container.revalidate();
            container.repaint();
    }
      private static void goBack() {
        if (!nav.isEmpty()) {
            JPanel previous = nav.pop();
            container.removeAll();
            container.add(previous);
            container.revalidate();
            container.repaint();
        }
    }
    public static void creatAndShowGUI(Data data)
    {
        mainFrame = new JFrame("Dictionary App");
        mainFrame.setSize(800, 500);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        container = new JPanel();
        mainFrame.add(container);
        

        JPanel menu = new JPanel(new GridLayout(4,2));

        swapPanel(menu);
        
        contentFeatures.forEach(item -> menu.add(new Button(item)));
    



        Button backButton = new Button("Go back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                goBack();
            };
        });


        java.awt.Component comp = menu.getComponent(0);
        if (comp instanceof java.awt.Button) {  
            java.awt.Button btn = (java.awt.Button) comp;
            btn.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) { 
                    JPanel wrap = new JPanel();
                    wrap.add(backButton);
                    wrap.add(SearchBySlangUI.createAndShowGUI(data));
                    swapPanel(wrap);
                }
            });
        }
          comp = menu.getComponent(1);
        if (comp instanceof java.awt.Button) {  
            java.awt.Button btn = (java.awt.Button) comp;
            btn.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) { 
                    JPanel wrap = new JPanel();
                    wrap.add(backButton);
                    wrap.add(SearchByDefinitionUI.createAndShowGUI(data));
                    swapPanel(wrap);
                }
            });
        }
        comp = menu.getComponent(2);
        if (comp instanceof java.awt.Button) {  
            java.awt.Button btn = (java.awt.Button) comp;
            btn.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) { 
                    JPanel wrap = new JPanel();
                    wrap.add(backButton);
                    wrap.add(HistoryUI.createAndShowGUI(data));
                    swapPanel(wrap);
                }
            });
        }
        
        mainFrame.setVisible(true);

    }
}
