package ui;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
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

        JTextField input = new JTextField();
        input.setColumns(10);
       
        JButton button = new JButton("OK");

        JPanel center = new JPanel(new FlowLayout());

        JPanel left =new JPanel();
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        left.add(input);

      
        JPanel outputList = new JPanel();
        outputList.setLayout(new BoxLayout(outputList, BoxLayout.Y_AXIS));

        button.addActionListener(new ButtonListener(input, outputList, data));

        left.add(outputList);
        JPanel right =new JPanel();
        right.add(button);

        center.add(left);
        center.add(right);


        result.add(center, BorderLayout.CENTER);
        return result;
    }
    
}
