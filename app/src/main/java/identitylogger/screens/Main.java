package identitylogger.screens;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import java.sql.Connection;
import java.awt.Dimension;
import java.awt.GridLayout;

import identitylogger.util.ReadProperties;
import identitylogger.database.DBConnection;
import identitylogger.screens.*;

public class Main {
    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("People Project");
        mainFrame.setSize(800,500);
        mainFrame.setMinimumSize(new Dimension(800,500));
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //mainFrame.setLayout(new GridLayout(1, 2));




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
         JScrollPane scrollPane = new JScrollPane(left, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


         MainSplitPane mainSplitPane = new MainSplitPane(scrollPane, right);

         JPanel controlPanel = new JPanel();

         JSplitPane jSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,controlPanel,mainSplitPane);
         jSplitPane.setDividerSize(-1);
         jSplitPane.setResizeWeight(0.05);

         mainFrame.add(jSplitPane);

         //mainFrame.add(mainSplitPane);
         mainFrame.setVisible(true);

         jSplitPane.setDividerLocation(0.05);

         //can only set after placing adding the splitFrame to mainFrame

         mainSplitPane.setDividerLocation(0.3); 
    }
}
