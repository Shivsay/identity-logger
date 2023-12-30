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
        String name;
        Date dob;
        
        try {
            String sqlPersonInfo = "SELECT id, name, dob FROM people where id = ?";
            PreparedStatement st = dbConn.prepareStatement(sqlPersonInfo);
            st.setInt(1, this.id);
            ResultSet rs = st.executeQuery();
            rs.next();

            name = rs.getString("name");
            dob = rs.getDate("dob");

            Person person = new Person(name,dob);

            return person;

        } catch(SQLException e) {
            e.printStackTrace();

            return null;
        }
    }
}
