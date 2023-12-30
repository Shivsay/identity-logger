package peopleproject.screens;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.awt.Dimension;
import java.awt.GridLayout;

import peopleproject.util.ReadProperties;
import peopleproject.database.DBConnection;
import peopleproject.screens.*;

public class Main {
    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("People Project");
        mainFrame.setSize(800,500);
        mainFrame.setMinimumSize(new Dimension(300,500));
        //mainFrame.setLayout(new GridLayout());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        ReadProperties rp = new ReadProperties();

        String[] up = rp.getUsernamePassword();
        if( up[0].isEmpty() || up[1].isEmpty()) {
            JOptionPane.showMessageDialog(mainFrame,"Username or Password not set");
            System.out.println("Username or Password not set");
            System.exit(1);
        }

         Connection dbConnection = new DBConnection().connect();

         SplitRight right = new SplitRight();
         SplitLeft left = new SplitLeft(right);


         MainSplitPane mainSplitPane = new MainSplitPane(left, right);

         mainFrame.add(mainSplitPane);
         mainFrame.setVisible(true);

         mainSplitPane.setDividerLocation(0.3); //can only set after placing adding the splitFrame to mainFrame
    }
}
