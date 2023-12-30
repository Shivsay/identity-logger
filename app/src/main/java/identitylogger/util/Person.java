package identitylogger.util;

import java.sql.Date;

public class Person {
    private String name, des;
    private Date dob;

    public Person(String name, Date dob) {
        this.name = name;
        this.dob = dob;
    }

    public String getName() {
        return this.name;
    }

    public Date getDOB() { 
        return this.dob;
    }
}
