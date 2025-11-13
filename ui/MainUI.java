package ui;
import models.*;
import java.util.List;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Stack;
public class MainUI {
    static List<String> contentFeatures = List.of("Search by slang word", 
    "Search by definition", "Histories search by slang word", 
    "Manage slang word", "Reset list of slang word", "Random slang word",
    "Quiz to find mean by slang word", "Quiz to find slang word by meaning");
   

    private static JFrame mainFrame;
    private static JPanel container;
    private static Stack<JPanel> nav = new Stack<>();

    private static  void swapPanel(JPanel panel) {
            if (container.getComponentCount() > 0) {
                nav.push((JPanel) container.getComponent(0)); 
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
        container.setBackground(Color.yellow);
        mainFrame.add(container, BorderLayout.CENTER);
        
        container.setBorder(BorderFactory.createEmptyBorder(50, 10, 10, 10));
        JPanel menu = new JPanel(new GridLayout(4,2, 10,10));

        
        menu.setPreferredSize(new Dimension(600, 300));

        swapPanel(menu);
        
        contentFeatures.forEach(item -> {
            Button btn = new Button(item);
            btn.setBackground(Color.blue);
            btn.setFont(new Font("Arial", Font.PLAIN, 18));
            btn.setForeground(Color.white);
            menu.add(btn);
        });
    

        

        Button backButton = new Button("Go back");
        backButton.setPreferredSize(new Dimension(100,20));

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
                    JPanel wrap = new JPanel(new BorderLayout());
                    wrap.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
                    wrap.add(backButton, BorderLayout.NORTH);
                    wrap.add(SearchBySlangUI.createAndShowGUI(data), BorderLayout.CENTER);
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
