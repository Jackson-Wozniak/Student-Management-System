package managementPanel;

import database.DataBaseConnection;
import studentInfo.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class TablePanel{

    DefaultTableModel model;
    JTable table;
    JScrollPane scrollPane;
    DataBaseConnection connection;

    public TablePanel(){
        this.connection = new DataBaseConnection();
        createTable();
    }

    public void createTable(){
        DefaultTableModel model = connection.getData();
        final JTable table = new JTable(model);
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        table.getColumnModel().getColumn(0).setMaxWidth(30);
        table.getColumnModel().getColumn(4).setMaxWidth(40);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

        JScrollPane scrollPane = new JScrollPane(table);
        this.table = table;
        this.model = model;
        this.scrollPane = scrollPane;
    }

    public JScrollPane getTableScrollable(){
        return this.scrollPane;
    }

    public void deleteTableRow(){
        if(table.getSelectedRow() != -1){
            int selection = table.getSelectedRow();
            String id = (String) table.getValueAt(table.getSelectedRow(), 0);
            model.removeRow(selection);
            JOptionPane.showMessageDialog(new JFrame(), "Student.Student Removed");
            this.connection.deleteFromDatabase(Integer.parseInt(id));
        }else{
            JOptionPane.showMessageDialog(new JFrame(), "Error in Removal", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    public void insertTableRow(Student student){
        model.insertRow(table.getRowCount(), student.getInfo());
        this.connection.addToDatabase(student.getInfo());
    }

    public boolean checkIdIsUnique(int id){
        int rowCount = table.getRowCount();
        for(int i = 0; i < rowCount; i++){
            if(Integer.parseInt((String) table.getValueAt(i, 0)) == id) return false;
        }
        return true;
    }
}
