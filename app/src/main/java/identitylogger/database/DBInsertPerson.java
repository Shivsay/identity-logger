package identitylogger.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBInsertPerson {
    Connection conn = new DBConnection().connect();

    public DBInsertPerson(String name, Date dob, String desc, String imageFilePath) {
        String sqlInsert = "INSERT INTO people (name,dob,description,imagePath) VALUES (?,?,?,?)";

        try {
            PreparedStatement st = conn.prepareStatement(sqlInsert);
            st.setString(1, name);
            st.setDate(2, dob);
            st.setString(3, desc);
            st.setString(4, imageFilePath);

            st.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
