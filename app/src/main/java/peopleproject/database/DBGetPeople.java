package peopleproject.database;



import java.beans.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Vector;

import peopleproject.util.People;
import peopleproject.database.DBConnection;

public class DBGetPeople {
    Connection dbConn = new DBConnection().connect();

 //   ArrayList<People> peopleList = new ArrayList<People>();
    Vector<People> peopleList = new Vector<People>();

    public DBGetPeople() {
    }


    public Vector<People> updatePeopleList() {
        
        try {
            String sqlGetIdName = "SELECT id, name FROM people";
            PreparedStatement st = dbConn.prepareStatement(sqlGetIdName);

            ResultSet rs = st.executeQuery();
            
            this.peopleList.clear();
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                System.out.println("ID: "+id+"Name: "+name);

                People person = new People(id,name);

                this.peopleList.add(person);
            }


        } catch(SQLException e) {
            e.printStackTrace();
        }

            return this.peopleList;
    }

    private int getTableCount() {

        try {
            String sqlCount = "SELECT COUNT(*) FROM people";
            PreparedStatement st = dbConn.prepareStatement(sqlCount);

            ResultSet rs = st.executeQuery(sqlCount);
            rs.next();

            return rs.getInt(1);
        } catch(SQLException e) {
            e.printStackTrace();
            return -1;
        }

    }
}
