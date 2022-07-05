package main;

import managementPanel.InputPanel;
import managementPanel.TablePanel;
import studentInfo.Student;

import javax.swing.*;
import java.awt.*;

public class mainFrame extends JFrame{

    public mainFrame(){
        this.setLayout(null);
        TablePanel tablePanel = new TablePanel();
        JScrollPane scrollPane = tablePanel.getTableScrollable();
        scrollPane.setBounds(260,10,500,200);

        InputPanel inputPanel = new InputPanel();
        JPanel inputJPanel = inputPanel.createPanel();
        inputJPanel.setBounds(0,0,250,200);
        this.add(inputJPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(100, 220, 600,40);
        buttonPanel.setLayout(new GridLayout(1, 4, 30, 0));
        JButton[] buttons = {new JButton("Add Student"), new JButton("Update Student"),
                new JButton("Remove User"), new JButton("Exit")};
        for (JButton button : buttons) {
            buttonPanel.add(button);
        }

        buttons[0].addActionListener(e -> { //add student button
            if(inputPanel.getText() != null){
                String[] studentInfo = inputPanel.getText();
                try {
                    Integer.parseInt(studentInfo[0]); //Make sure student ID is a valid number
                } catch(NumberFormatException ex) {
                    JOptionPane.showMessageDialog(new JFrame(), "ID Must be a positive number",
                            "ID Invalid", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(!tablePanel.checkIdIsUnique(Integer.parseInt(studentInfo[0]))){
                    JOptionPane.showMessageDialog(new JFrame(), "ID Exists in System",
                            "Invalid ID", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Student createStudent = new Student(studentInfo);
                tablePanel.insertTableRow(createStudent);
            }
        });

        buttons[1].addActionListener(e -> { //update student
            //in progress
        });

        buttons[2].addActionListener( e -> { //remove student
            tablePanel.deleteTableRow();
        });

        buttons[3].addActionListener(e -> { //exit
            System.exit(0);
        });

        this.add(buttonPanel);
        this.add(scrollPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800,450);
        this.setVisible(true);
    }
}
