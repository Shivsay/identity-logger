package peopleproject.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {

    String username, password;
    public ReadProperties() {
        accessPropertyFile();
        //getProperties();
    }

    private void accessPropertyFile() {
        Properties properties = new Properties();
        try {
            FileInputStream fis = new FileInputStream("./src/main/resources/application.properties");

            properties.load(fis);
            this.username = properties.getProperty("username");
            this.password = properties.getProperty("password");
        } catch (IOException e) {

            System.out.println("ERROR: File application.properties not set properly!");
            e.printStackTrace();
            System.exit(1);
        }
    }
    public String[] getUsernamePassword() {
        String[] returnProperties = {this.username, this.password};
        return returnProperties;
    }
}
