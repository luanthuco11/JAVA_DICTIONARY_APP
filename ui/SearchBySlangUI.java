package ui;
import models.*;

import javax.swing.*;
import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

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
        result.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); 

    
        JPanel center = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); 
        gbc.fill = GridBagConstraints.HORIZONTAL;

       
        JLabel inputLabel = new JLabel("Input:");
        gbc.gridx = 0;
        gbc.gridy = 0;
       
        center.add(inputLabel, gbc);

        JTextField input = new JTextField();
        input.setColumns(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
      
        center.add(input, gbc);

      
        JLabel outputLabel = new JLabel("Output:");
        gbc.gridx = 0;
        gbc.gridy = 1;
      
        center.add(outputLabel, gbc);

        JLabel output = new JLabel(" ");
        output.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        output.setFont(new Font("Arial", Font.PLAIN, 15));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1;
        center.add(output, gbc);

   
        JButton button = new JButton("Confirm");
        button.addActionListener(new ButtonListener(input, output, data));

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 2; 
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.VERTICAL;
        center.add(button, gbc);

        result.add(center, BorderLayout.CENTER);
        return result;
    }
}
