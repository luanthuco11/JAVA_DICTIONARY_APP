package ui;
import models.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchBySlangUI {
    public static JPanel creatAndShowGUI(){
        JPanel result = new JPanel(new BorderLayout());

        JTextField input = new JTextField();
        input.setColumns(10);
        JLabel output = new JLabel("Output");
        JButton button = new JButton("OK");

        JPanel center = new JPanel(new FlowLayout());

        JPanel left =new JPanel();
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        left.add(input);
        left.add(output);
        JPanel right =new JPanel();
        right.add(button);

        center.add(left);
        center.add(right);


        result.add(center, BorderLayout.CENTER);
        return result;
    }
}
