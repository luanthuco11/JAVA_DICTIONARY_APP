package ui;

import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import models.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class QuizUI {

    public static JPanel displayBoard (String ques, List<String> respo, String correctResponse , SlangWordManager data, String type){
        JPanel result = new JPanel(new BorderLayout());

        JPanel questionContainer = new JPanel();
       JLabel question = new JLabel(ques);
        question.setFont(new Font("Arial", Font.BOLD, 18));
        question.setForeground(Color.decode("#333333"));
        question.setHorizontalAlignment(SwingConstants.CENTER);
        Border line = BorderFactory.createLineBorder(Color.decode("#80B3FF"), 2); 
        Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);          
        question.setBorder(BorderFactory.createCompoundBorder(line, padding));
        

        questionContainer.add(question);

        JPanel responseContainer = new JPanel(new GridLayout(2,2, 10, 10));

       respo.forEach(res ->{
            JButton response = new JButton(res);
            response.setCursor(new Cursor(Cursor.HAND_CURSOR));
            response.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    if(res.equals(correctResponse)){
                         JOptionPane.showMessageDialog(null, "You are right", "Result", JOptionPane.INFORMATION_MESSAGE);
                    }else
                    {
                        JOptionPane.showMessageDialog(null, "You are wrong", "Result", JOptionPane.INFORMATION_MESSAGE);
                    }
                     Container parent = result.getParent(); 
                    if(parent != null){
                        parent.remove(result);
                        parent.add(QuizUI.createAndShowGUI(data, type)); 
                        parent.revalidate();
                        parent.repaint();
                    }
                }

            });
         responseContainer.add(response);
       });  
       
        result.add(questionContainer, BorderLayout.CENTER);
        result.add(responseContainer, BorderLayout.SOUTH);

        return result;
    }
    public static JPanel  createAndShowGUI(SlangWordManager data, String type)
    {
        List<String> listKey = new ArrayList<>(data.listSlang.keySet());
        Random random = new Random();
        int range = data.listSlang.size();
        int randomNumber = random.nextInt(range);

        List<Number> listReposInt = new ArrayList<>();
        List<String> fakeRes = new ArrayList<>();

        for(int i = 0; i < 3; i++)
        {     
            int randomFake = random.nextInt(range);
            while(randomFake == randomNumber){
                randomFake = random.nextInt(range);
            }
            listReposInt.add(randomFake);
        }
        if(type.equals("slang")){
           
            String question = listKey.get(randomNumber);
            String correctRes = data.listSlang.get(question).getMean();

            listReposInt.forEach( numb ->{
                fakeRes.add(data.listSlang.get(listKey.get(numb.intValue())).getMean());
            });

            
            fakeRes.add(correctRes);
            Collections.shuffle(fakeRes);
            return displayBoard(question, fakeRes, correctRes, data,type);

        }else {
                String question = data.listSlang.get(listKey.get(randomNumber)).getMean();
                String correctRes = listKey.get(randomNumber);

                listReposInt.forEach( numb ->{
                    fakeRes.add(listKey.get(numb.intValue()));
                });
                fakeRes.add(correctRes);
                Collections.shuffle(fakeRes);
                return displayBoard(question, fakeRes, correctRes, data, type);
        }
        
      
    }
}
