package peopleproject.screens;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String getNoRows = "select count(*) from people";
        String sql = "select name from people";

        
        Class.forName("org.mariadb.jdbc.Driver");
        System.out.println("Driver loaded");

        //put try catch here
        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost/peopleViewer", "jproject", "password");
        
        System.out.println("It works!");

        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(getNoRows);
        rs.next();

        int noOfItems = rs.getInt(1);
        System.out.println(noOfItems);

        rs = st.executeQuery(sql);

        for(int i=0;i<noOfItems;i++) {
        rs.next();
        String name = rs.getString(1);

        System.out.println(name);
        }
        connection.close();
    }
}
