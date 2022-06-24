package managementPanel;

import studentInfo.ListOfMajors;

import javax.swing.*;
import java.awt.*;

public class InputPanel extends JPanel {

    JTextField[] inputText;
    JComboBox<String> majorComboBox;
    JComboBox<String> yearComboBox;

    public JPanel createPanel(){
        this.setLayout(null);
        JTextField[] textArray = {new JTextField(), new JTextField(), new JTextField()};
        for(int i = 0; i < textArray.length; i++){
            textArray[i].setBounds(100, 10 + (40 * i), 150, 30);
            this.add(textArray[i]);
        }
        this.inputText = textArray;

        majorComboBox = new JComboBox<>(ListOfMajors.majors);
        majorComboBox.setBounds(100, 130, 150, 30);
        this.add(majorComboBox);

        String[] year = {"Un-Enrolled","1", "2", "3", "4", "Graduate"};
        yearComboBox = new JComboBox<>(year);
        yearComboBox.setBounds(100, 170, 150, 30);
        this.add(yearComboBox);

        createLabels();
        this.setPreferredSize(new Dimension(250, 200));

        return this;
    }

    public void createLabels(){
        JLabel[] infoLabels = {new JLabel("ID"), new JLabel("First Name"),
                new JLabel("Last Name"), new JLabel("Major"), new JLabel("Year")};
        for(int i = 0; i < infoLabels.length; i++){
            infoLabels[i].setBounds(10, 10 + (40 * i), 90, 30);
            this.add(infoLabels[i]);
        }
    }

    public String[] getText(){
        String[] textInfo = new String[5];
        for(int i = 0; i < 3; i++){
            if(!inputText[i].getText().isEmpty()){
                textInfo[i] = inputText[i].getText();
            }else{
                JOptionPane.showMessageDialog(new JFrame(), "Empty Input Text",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }
        textInfo[3] = (String) majorComboBox.getSelectedItem();
        textInfo[4] = (String) yearComboBox.getSelectedItem();
        return textInfo;
    }

}
