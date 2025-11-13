package ui;
import java.awt.BorderLayout;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;
import java.awt.*;
import javax.swing.BorderFactory;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.util.List;
import java.awt.event.*;
import models.*;
public class SearchByDefinitionUI {



    static class ButtonListener implements ActionListener {
        private JTextField input;
        private JPanel output;
        private Data data;
        public ButtonListener(JTextField input, JPanel output, Data data) {
            this.input = input;
            this.output = output;
            this.data = data;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
          
           String value = input.getText();
     
           List<String> results = data.listSlang.get(value);
           if (results != null) {
                JList<String> list = new JList<>(results.toArray(new String[0]));
                output.removeAll();
                output.add(new JScrollPane(list));
                output.revalidate();
                output.repaint();
            }else{
                output.removeAll();
            }
          
            
        }
    }
    public static JPanel createAndShowGUI(Data data){
        JPanel result = new JPanel(new BorderLayout());
        result.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); 

        JPanel center = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel inputLabel = new JLabel("Input");
        gbc.gridx = 0;
        gbc.gridy = 0;

        center.add(inputLabel,gbc);
        
        JTextField input = new JTextField();
        input.setColumns(20);
        gbc.gridx = 1;
        gbc.gridy = 0;

        center.add(input, gbc);
        
        
       

        JPanel outputList = new JPanel();
        gbc.gridx = 0;
        gbc.gridx = 1;
        gbc.weighty = 1;
        outputList.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        center.add(outputList, gbc);
       
        JButton button = new JButton("Confirm");
        button.addActionListener(new ButtonListener(input, outputList, data));
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 2;

        center.add(button, gbc);


        result.add(center, BorderLayout.CENTER);
        return result;
    }
    
}
