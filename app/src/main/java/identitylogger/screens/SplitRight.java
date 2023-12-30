package identitylogger.screens;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.sql.Date;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import identitylogger.util.Person;
import identitylogger.database.DBGetPerson;

public class SplitRight extends JSplitPane {
    String name;
    Date dob;
    Person person;

    JPanel lPanel, rPanel;

    public SplitRight() {
        super.setDividerSize(0);
        //super.setResizeWeight(0.2);
        this.updatePerson(1);       //TODO
                                    //
        JPanel lPanel = leftPanel();
        JPanel rPanel = rightPanel();
        super.setLeftComponent(lPanel);
        super.setRightComponent(rPanel);


    }

    public void updatePerson(int id) {
        DBGetPerson dbGetPerson = new DBGetPerson(id);
        this.person = dbGetPerson.getPerson();

        this.updatePanels();
    }

    private JPanel leftPanel() {
        JPanel jLeftPanel = new JPanel();
        jLeftPanel.setLayout(new BoxLayout(jLeftPanel, BoxLayout.Y_AXIS));
        JLabel iconLabel = new JLabel();
        iconLabel.setSize(300,300);
        ImageIcon iconLabelIcon = getResourceImage("./src/main/resources/personImages/default-avatar.jpg", iconLabel);
        iconLabel.setIcon(iconLabelIcon);
        jLeftPanel.add(iconLabel);

        return jLeftPanel;
    }

    private JPanel rightPanel() {
        JPanel jRightPanel = new JPanel();
        jRightPanel.setBackground(Color.RED);
        jRightPanel.setLayout(new BoxLayout(jRightPanel, BoxLayout.Y_AXIS));
        JLabel jRightLabelName = new JLabel(person.getName());
        JLabel jRightLabelDesc = new JLabel(person.getName());
        jRightPanel.add(jRightLabelName);
        jRightPanel.add(jRightLabelDesc);

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
