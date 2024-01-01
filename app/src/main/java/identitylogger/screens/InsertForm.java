package identitylogger.screens;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.text.ParseException;

import identitylogger.database.DBInsertPerson;
import identitylogger.screens.ControlPane;

public class InsertForm extends JFrame {
    private JTextField nameField, dobField;
    private JTextArea descField;
    private SplitLeft list;
    private ControlPane cntrlPanel;

    public InsertForm(SplitLeft list, ControlPane cntrlPanel) {
        this.cntrlPanel =  cntrlPanel;
        this.list = list;
        super.setTitle("Insert New Person");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setMinimumSize(new Dimension(400,300));
        super.setSize(700, 500);

        nameField = new JTextField(20);
        dobField = new JTextField(20);
        descField = new JTextArea(5,20);
        descField.setWrapStyleWord(true);
        descField.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(descField, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JButton insertButton = new JButton("Insert Data");
                insertButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        insertData();
                    }
                });

        JButton cancelButton = new JButton("Cancle");
                cancelButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cntrlPanel.insertBtn.setEnabled(true);
                        dispose();
                    }
                });

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Date (YYYY-MM-DD):"));
        panel.add(dobField);
        panel.add(new JLabel("Text:"));
        panel.add(scrollPane);
        //panel.add(new JLabel()); // Empty label for spacing
        panel.add(insertButton);
        panel.add(cancelButton);

        add(panel);

        setVisible(true);
    }

    private void insertData() {
        String name = nameField.getText();
        String date = dobField.getText();
        String desc = descField.getText();

        DateFormat dobformatter= new SimpleDateFormat("yyyy-MM-dd");
        try {
        Date dobDate = dobformatter.parse(date);

        java.sql.Date sqlDate = new java.sql.Date(dobDate.getTime());


        if(name.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Name field cannot be empty");
            return;
        }

        DBInsertPerson dbInsert = new DBInsertPerson(name,sqlDate,desc);

        //super.dispatchEvent(new WindowEvent(this,WindowEvent.WINDOW_CLOSING));
        list.updateList();
        System.out.println("Updated");
        this.dispose();

        this.cntrlPanel.insertBtn.setEnabled(true);

        return;

        } catch(ParseException e) {
            JOptionPane.showMessageDialog(null, "Date not set correctly");
        this.cntrlPanel.insertBtn.setEnabled(true);
            return;
        }
    }
}
