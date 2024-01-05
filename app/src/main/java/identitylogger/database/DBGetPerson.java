package identitylogger.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import identitylogger.util.Person;

public class DBGetPerson {
    int id;
    Connection dbConn = new DBConnection().connect();

    public DBGetPerson(int id) {
        this.id = id;
    }

    public Person getPerson() {
        String name, description, imagePath;
        Date dob;
        
        try {
            String sqlPersonInfo = "SELECT id, name, dob, description, imagePath FROM people where id = ?";
            PreparedStatement st = dbConn.prepareStatement(sqlPersonInfo);
            st.setInt(1, this.id);
            ResultSet rs = st.executeQuery();
            rs.next();

            name = rs.getString("name");
            dob = rs.getDate("dob");
            description = rs.getString("description");
            imagePath = rs.getString("imagePath");

            Person person = new Person(name,dob,description,imagePath);

            return person;

        } catch(SQLException e) {
            e.printStackTrace();

            return null;
        }
    }
}
