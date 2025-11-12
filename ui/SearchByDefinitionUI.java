package ui;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.util.List;
public class SearchByDefinitionUI {
    public static JPanel creatAndShowGUI(){
        JPanel result = new JPanel(new BorderLayout());

        JTextField input = new JTextField();
        input.setColumns(10);
       
        JButton button = new JButton("OK");

        JPanel center = new JPanel(new FlowLayout());

        JPanel left =new JPanel();
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        left.add(input);

        List<String> outputs = List.of("Nam", "Nu", "Nha", "Ai");
        JPanel outputList = new JPanel();
        outputList.setLayout(new BoxLayout(outputList, BoxLayout.Y_AXIS));


        outputs.forEach(item -> outputList.add(new JLabel(item)));
        left.add(outputList);
        JPanel right =new JPanel();
        right.add(button);

        center.add(left);
        center.add(right);


        result.add(center, BorderLayout.CENTER);
        return result;
    }
    
}
