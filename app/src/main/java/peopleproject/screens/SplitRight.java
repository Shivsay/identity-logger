package peopleproject.screens;

import java.awt.Color;
import java.awt.FlowLayout;
import java.sql.Date;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import peopleproject.util.Person;
import peopleproject.database.DBGetPerson;

public class SplitRight extends JPanel {
    String name;
    Date dob;
    Person person;

    public SplitRight() {
        this.setLayout(new FlowLayout());
        //this.setBackground(Color.BLUE);
        this.updatePerson(1);       //TODO
        JPanel lPanel = leftPanel();
        this.add(lPanel);
    }

    public void updatePerson(int id) {
        DBGetPerson dbGetPerson = new DBGetPerson(id);
        this.person = dbGetPerson.getPerson();
    }

    private JPanel leftPanel() {
        JPanel jLeftPanel = new JPanel(new BoxLayout(this, BoxLayout.Y_AXIS));
        jLeftPanel.add(new JLabel(person.getName()));

        return jLeftPanel;
    }

}
