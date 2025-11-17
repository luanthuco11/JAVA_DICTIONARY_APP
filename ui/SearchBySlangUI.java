package ui;
import models.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class SearchBySlangUI {


    static class ButtonListener implements ActionListener {
        private JTextField input;
        private JTextArea output;
        private SlangWordManager data;
        public ButtonListener(JTextField input, JTextArea output, SlangWordManager data) {
            this.input = input;
            this.output = output;
            this.data = data;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
           String value = input.getText();
           if(!value.equals("")){
                SlangWord result = data.listSlang.get(value);
                if(result != null){
                    String mean = result.getMean();   
                    if(!mean.isEmpty()) data.histories.add(value + "`" + mean);
                            output.setText(mean);
                            output.setCaretPosition(0);
                    }else{
                        output.setText(" ");
                }
           }else{
             output.setText(" ");
           }
            
           output.revalidate();
           output.repaint();
            
        }
    }

    public static JPanel createAndShowGUI(SlangWordManager data){
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

        JTextArea output = new JTextArea(2, 20);
        output.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        output.setFont(new Font("Arial", Font.PLAIN, 15));
        output.setEditable(false);        
        output.setLineWrap(false);       
        output.setWrapStyleWord(false); 
        JScrollPane scroll = new JScrollPane(output,JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);    
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1;
        center.add(scroll, gbc);

   
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
