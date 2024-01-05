package identitylogger.util;

import java.sql.Date;

public class Person {
    private String name, description, imagePath;
    private Date dob;

    public Person(String name, Date dob, String description, String imagePath) {
        this.name = name;
        this.dob = dob;
        this.description = description;
        this.imagePath = imagePath;
    }

    public String getName() {
        return this.name;
    }

    public Date getDOB() { 
        return this.dob;
    }

    public String getDescription() {
        return this.description;
    }

    public String getImagePath() {
        return this.imagePath;
    }
}
