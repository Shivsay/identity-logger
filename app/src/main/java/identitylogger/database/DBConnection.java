package identitylogger.database;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

import identitylogger.util.ReadProperties;

public class DBConnection {
    private Connection dbConnection;

    public Connection connect() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            System.out.println("Driver Found!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Driver Not Found!");
            System.out.println("Driver Not Found!" + e);
            System.exit(1);
        }

        try {
            String[] userInfo = new ReadProperties().getUsernamePassword();

            dbConnection = DriverManager.getConnection("jdbc:mariadb://localhost/peopleViewer", userInfo[0], userInfo[1]);
            System.out.println("Database Connected!");
        } catch (Exception se) {
            JOptionPane.showMessageDialog(null, "Database Connection Failed!");
            System.out.println("Database Connection Failed!" + se);
            System.exit(1);
        }
        return dbConnection;
    }
}
