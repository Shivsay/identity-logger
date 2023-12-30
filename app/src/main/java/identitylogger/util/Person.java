package identitylogger.util;

import java.sql.Date;

public class Person {
    private String name, description;
    private Date dob;

    public Person(String name, Date dob, String description) {
        this.name = name;
        this.dob = dob;
        this.description = description;
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
}
