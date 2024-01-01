package identitylogger.screens;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import java.awt.Font;

import identitylogger.database.DBDeletePerson;
import identitylogger.util.Person;
import identitylogger.database.DBGetPerson;

public class SplitRight extends JSplitPane {
    String name;
    Date dob;
    Person person;
    int id;

    JPanel lPanel, rPanel;

    public SplitRight() {
        super.setDividerSize(-1);
        this.updatePerson(0);       //TODO

    }

    public void updatePerson(int id) {
        this.id = id;
        DBGetPerson dbGetPerson = new DBGetPerson(id);
        if (id == 0) {
            super.setLeftComponent(null);
            super.setRightComponent(null);
            return;
        }
        this.person = dbGetPerson.getPerson();



        this.updatePanels();
    }

    private JPanel leftPanel() {
        JPanel jLeftPanel = new JPanel();
        jLeftPanel.setLayout(new BoxLayout(jLeftPanel, BoxLayout.Y_AXIS));
        JLabel iconLabel = new JLabel();
        iconLabel.setSize(300,300);
        ImageIcon iconLabelIcon = getResourceImage("./src/main/resources/personImages/default-avatar.jpg", iconLabel);

        Date dob = person.getDOB();
        jLeftPanel.add(iconLabel);
        JLabel dobLabel = new JLabel("Dob: "+dob);
        dobLabel.setBorder(new EmptyBorder(10, 10, 10, 10));

        iconLabel.setIcon(iconLabelIcon);
        jLeftPanel.add(dobLabel);

        return jLeftPanel;
    }

    private JPanel rightPanel() {
        JPanel jRightPanel = new JPanel();
        jRightPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        jRightPanel.setLayout(new BoxLayout(jRightPanel, BoxLayout.Y_AXIS));
        JLabel jRightLabelName = new JLabel(person.getName());
        jRightLabelName.setFont(new Font("Arial", Font.PLAIN, 34));

        JTextArea jRightTextDesc = new JTextArea(person.getDescription());
        jRightTextDesc.setWrapStyleWord(true);
        jRightTextDesc.setLineWrap(true);
        jRightTextDesc.setEditable(false);

        JScrollPane jDescPane = new JScrollPane(jRightTextDesc,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jDescPane.setBorder(new EmptyBorder(30, 0, 10, 0));

        JButton deleteButton = new JButton("Delete");
            deleteButton.addActionListener(new ActionListener() {
                @Override
                 public void actionPerformed(ActionEvent e) {
                     new DBDeletePerson(id);
                     updatePerson(0);       //TODO
                 }
            });

        jRightPanel.add(jRightLabelName);
        jRightPanel.add(jDescPane);

        jRightPanel.add(deleteButton);

        return jRightPanel;
    }

    private void updatePanels() {
        JPanel lPanel = leftPanel();
        JPanel rPanel = rightPanel();
        super.setLeftComponent(lPanel);
        super.setRightComponent(rPanel);
    }


    private ImageIcon getResourceImage(String filepath, JLabel label){
            Image rawImage = new ImageIcon(filepath).getImage();
            Image renderedImage = rawImage.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon image = new ImageIcon(renderedImage);
            return image;

        }

}
