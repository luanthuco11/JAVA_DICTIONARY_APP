package ui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.util.List;


import models.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ManageUI {
    
    public static JPanel createAndShowGUI(SlangWordManager data)
    {
        JPanel result = new JPanel(new BorderLayout());
        
        result.setBackground(Color.BLUE);
        
        String[] colsName = {"Old Slang","Old mean", "Slang", "Mean"};
        
        DefaultTableModel dataTable = new DefaultTableModel(colsName, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0 || column == 1) return false;
                return true; 
            }
        };
        
        dataTable.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent  e) {
            
                int rowIndex = e.getFirstRow();
                int colIndex = e.getColumn(); 
                if(rowIndex >= 0 && colIndex >= 0){
                    String oldKey = dataTable.getValueAt(rowIndex, 0).toString();
                    String oldMean = dataTable.getValueAt(rowIndex, 1).toString();
                    String newKey = dataTable.getValueAt(rowIndex, 2).toString();
                    String newMean =  dataTable.getValueAt(rowIndex, 3).toString();
                    data.editSlangWord(new SlangWord(oldKey, oldMean), new SlangWord(newKey, newMean));     
                }        
        }
    }) ;
        
            
      

        for (SlangWord value : data.listSlang.values()) {
            dataTable.addRow(List.of(value.getSlang(), value.getMean(), value.getSlang(),value.getMean()).toArray());
        }

        JTable table = new JTable(dataTable);
        
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(dataTable);
        table.setRowSorter(sorter);  
        sorter.toggleSortOrder(0);  

        JScrollPane scroll = new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(0, 300));
        JPanel addSection = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        JLabel labelSlang = new JLabel("Slang");
        gbc.gridx = 0;
        gbc.gridy = 0;
        addSection.add(labelSlang, gbc);

        JLabel labelMean= new JLabel("Mean");
        gbc.gridx = 0;
        gbc.gridy = 1;
        addSection.add(labelMean, gbc);

        
        JTextField inputSlang = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        addSection.add(inputSlang, gbc);

        JTextField inputMean= new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        addSection.add(inputMean, gbc);

        JButton addBtn = new JButton("Add");
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        
        addBtn.addActionListener(new ActionListener() {
           
            @Override
            public void actionPerformed(ActionEvent e) {
                String slang = inputSlang.getText();
                String mean = inputMean.getText();
                if(!slang.equals("") && !mean.equals("") ){
                    
                    if(data.isExist(slang)){
                        Object[] options = {"Overwrite", "Duplicate"};

                        int option = JOptionPane.showOptionDialog(
                                result,
                                "A slang already exists. What do you want to do?",
                                "Conflict Detected",
                                JOptionPane.YES_NO_OPTION,           
                                JOptionPane.QUESTION_MESSAGE,       
                                null,                             
                                options,                            
                                options[0]                          
                        );

                        
                        if (option == JOptionPane.YES_OPTION) {
                            data.addSlangOverwrite(new SlangWord(slang, mean));
                      
                        } else if (option == JOptionPane.NO_OPTION) {
                            data.addSlangDup(new SlangWord(slang, mean));
                            
                        } 
                    }else{
                        data.addSlangOverwrite(new SlangWord(slang, mean));
                    }
                    inputMean.setText("");
                    inputSlang.setText("");
                }
                
            }
        });
        addBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addSection.add(addBtn, gbc);

        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem deleteItem = new JMenuItem("Delete");
        popupMenu.add(deleteItem);

        deleteItem.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                 Object[] options = {"Yes", "No"};

                        int option = JOptionPane.showOptionDialog(
                                result,
                                "Are you sure?",
                                "Notification",
                                JOptionPane.YES_NO_OPTION,           
                                JOptionPane.QUESTION_MESSAGE,       
                                null,                             
                                options,                            
                                options[0]                          
                        );

                        
                        if (option == JOptionPane.YES_OPTION) {
                           
                            int modelRow = table.convertRowIndexToModel(row);
                            data.deleteSlangWord(dataTable.getValueAt(modelRow, 0).toString());
                            dataTable.removeRow(modelRow);;
                        }
               
            }
           
        });
        table.setComponentPopupMenu(popupMenu);
        result.add(addSection, BorderLayout.NORTH);
        result.add(scroll, BorderLayout.CENTER);

        return result;
    }
}
