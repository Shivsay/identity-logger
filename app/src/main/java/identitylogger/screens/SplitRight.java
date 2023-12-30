package identitylogger.screens;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.sql.Date;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import identitylogger.util.Person;
import identitylogger.database.DBGetPerson;

public class SplitRight extends JSplitPane {
    String name;
    Date dob;
    Person person;

    JPanel lPanel, rPanel;

    public SplitRight() {
        super.setDividerSize(-1);
        //super.setResizeWeight(0.2);
        this.updatePerson(0);       //TODO

        /*JPanel lPanel = leftPanel();
        JPanel rPanel = rightPanel();
        super.setLeftComponent(lPanel);
        super.setRightComponent(rPanel);*/


    }

    public void updatePerson(int id) {
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
        //jRightPanel.setBackground(Color.RED);
        jRightPanel.setLayout(new BoxLayout(jRightPanel, BoxLayout.Y_AXIS));
        JLabel jRightLabelName = new JLabel(person.getName());
        jRightLabelName.setFont(new Font("Arial", Font.PLAIN, 34));
        /*JLabel jDescriptionText = new JLabel("Description:");
        jDescriptionText.setBorder(new EmptyBorder(10, 0, 10, 0));
        jDescriptionText.setFont(new Font("Arial", Font.PLAIN, 24));*/

        JTextArea jRightTextDesc = new JTextArea(person.getDescription());
        jRightTextDesc.setWrapStyleWord(true);
        jRightTextDesc.setLineWrap(true);
        //jRightTextDesc.setOpaque(false);
        jRightTextDesc.setEditable(false);

        JScrollPane jDescPane = new JScrollPane(jRightTextDesc,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jDescPane.setBorder(new EmptyBorder(30, 0, 10, 0));

        jRightPanel.add(jRightLabelName);
        //jRightPanel.add(jDescriptionText);
        jRightPanel.add(jDescPane);

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
