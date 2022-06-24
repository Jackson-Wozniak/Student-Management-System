package database;

import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class DataBaseConnection {
    private Connection connect;

    public DataBaseConnection(){
        try{
            this.connect = DriverManager.getConnection(MySQL_Login.database, MySQL_Login.username,
                    MySQL_Login.password);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public DefaultTableModel getData(){
        String[] columnNames = { "ID", "First Name", "Last Name", "Major", "Year"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        String sql = "select * from students";
        try{
            Statement statement = connect.createStatement();
            ResultSet data;
            data = statement.executeQuery(sql);
            while(data.next()){
                String id = data.getString(1);
                String firstName = data.getString(2);
                String lastName = data.getString(3);
                String major = data.getString(4);
                String year = data.getString(5);
                model.insertRow(model.getRowCount(), new Object[] {id, firstName, lastName, major, year});
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return model;
    }

    public void addToDatabase(String[] info){
        try {
            PreparedStatement preparedStatement = connect.prepareStatement(
                    "insert into students values(?,?,?,?,?)");
            preparedStatement.setInt(1, Integer.parseInt(info[0]));
            preparedStatement.setString(2, info[1]);
            preparedStatement.setString(3, info[2]);
            preparedStatement.setString(4, info[3]);
            preparedStatement.setString(5, info[4]);

            preparedStatement.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void deleteFromDatabase(int rowCount){
        try{
            PreparedStatement preparedStatement = connect.prepareStatement("delete from students where student_id=? ");
            preparedStatement.setInt(1, rowCount);
            preparedStatement.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
