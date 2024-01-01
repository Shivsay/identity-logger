package identitylogger.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBDeletePerson {
    Connection conn = new DBConnection().connect();

    public DBDeletePerson(int dbID) {
        String sqlDelete = "DELETE FROM people where id = ?";

        try {
            PreparedStatement st = conn.prepareStatement(sqlDelete);
            st.setInt(1,dbID);
            st.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
