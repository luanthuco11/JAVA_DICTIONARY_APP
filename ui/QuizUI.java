package ui;

import javax.swing.JPanel;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.xml.crypto.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

import models.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class QuizUI {

    public static JPanel displayBoard (String ques, List<String> respo, String correctResponse){
        JPanel result = new JPanel(new BorderLayout());

        JPanel questionContainer = new JPanel();
        JLabel question = new JLabel(ques);
        questionContainer.add(question);

        JPanel responseContainer = new JPanel(new GridLayout(2,2));

       respo.forEach(res ->{
            JButton response = new JButton(res);
        
            response.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    if(res.equals(correctResponse)){
                         JOptionPane.showMessageDialog(result, "You are right", "Result", JOptionPane.INFORMATION_MESSAGE);
                    }else
                    {
                        JOptionPane.showMessageDialog(result, "You are wrong", "Result", JOptionPane.INFORMATION_MESSAGE);
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
        List<String> listKey = new ArrayList<>(data.listHashMap.keySet());
        Random random = new Random();
        int range = data.listHashMap.size();
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
            String correctRes = data.listHashMap.get(question).getMean();

            listReposInt.forEach( numb ->{
                fakeRes.add(data.listHashMap.get(listKey.get(numb.intValue())).getMean());
            });

            
            fakeRes.add(correctRes);
            Collections.shuffle(fakeRes);
            return displayBoard(question, fakeRes, correctRes);

        }else {
                String question = data.listHashMap.get(listKey.get(randomNumber)).getMean();
                String correctRes = listKey.get(randomNumber);

                listReposInt.forEach( numb ->{
                    fakeRes.add(listKey.get(numb.intValue()));
                });

                
                fakeRes.add(correctRes);
                Collections.shuffle(fakeRes);
                return displayBoard(question, fakeRes, correctRes);
        }

      
    }
}
