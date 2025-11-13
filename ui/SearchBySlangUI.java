package ui;
import models.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.*;
public class SearchBySlangUI {


    static class ButtonListener implements ActionListener {
        private JTextField input;
        private JLabel output;
        private Data data;
        public ButtonListener(JTextField input, JLabel output, Data data) {
            this.input = input;
            this.output = output;
            this.data = data;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
           String value = input.getText();
            String result = data.listHashMap.get(value);
            if(!result.isEmpty()) data.histories.add(value + "`" + result);
           output.setText(result);
            
        }
    }

    public static JPanel createAndShowGUI(Data data){
        JPanel result = new JPanel(new BorderLayout());

        JTextField input = new JTextField();
        input.setColumns(10);

        JLabel output = new JLabel("");

        JButton button = new JButton("OK");

        button.addActionListener(new ButtonListener(input, output, data));

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
