package identitylogger.screens;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
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
import identitylogger.util.ImageFilter;

public class InsertForm extends JFrame {
    private JTextField nameField, dobField;
    private JTextArea descField;
    private SplitLeft list;
    private ControlPane cntrlPanel;

    private String imageFile;

    public InsertForm(SplitLeft list, ControlPane cntrlPanel) {
        this.cntrlPanel =  cntrlPanel;
        this.list = list;
        super.setTitle("Insert New Person");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setMinimumSize(new Dimension(650,700));
        super.setMaximumSize(new Dimension(900,1000));
        super.setSize(800, 700);

        nameField = new JTextField(20);
        dobField = new JTextField(20);
        descField = new JTextArea(20,50);
        descField.setWrapStyleWord(true);
        descField.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(descField, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JFileChooser fChooser = new JFileChooser(); 
        fChooser.addChoosableFileFilter(new ImageFilter());
        fChooser.setAcceptAllFileFilterUsed(false);
        JLabel imageFileLabel = new JLabel("File:");

        JButton fileChooserButton = new JButton("Choose Image");
                fileChooserButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        fChooser.showDialog(null, "Select");

                        imageFile = fChooser.getSelectedFile().getPath();

                        imageFileLabel.setText("Selected File:" +imageFile);

                    }
                });

        JButton insertButton = new JButton("Insert Data");
                insertButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        insertData();
                    }
                });

        JButton cancelButton = new JButton("Cancel");
                cancelButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cntrlPanel.insertBtn.setEnabled(true);
                        dispose();
                    }
                });

        //JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel datePanel = new JPanel((new FlowLayout(FlowLayout.LEFT)));
        JPanel descLabelPanel = new JPanel((new FlowLayout(FlowLayout.LEFT)));
        JPanel descTextPanel = new JPanel((new FlowLayout(FlowLayout.LEFT)));
        JPanel filePanel = new JPanel((new FlowLayout(FlowLayout.LEFT)));
        JPanel btnPanel = new JPanel((new FlowLayout(FlowLayout.LEFT)));

        namePanel.add(new JLabel("Name:"));
        namePanel.add(nameField);
        panel.add(namePanel);

        datePanel.add(new JLabel("Date (YYYY-MM-DD):"));
        datePanel.add(dobField);
        panel.add(datePanel);

        descLabelPanel.add(new JLabel("Description:"));
        descTextPanel.add(scrollPane);
        panel.add(descLabelPanel);
        panel.add(descTextPanel);


        filePanel.add(fileChooserButton);
        filePanel.add(imageFileLabel);
        panel.add(filePanel);
                          
        btnPanel.add(insertButton);
        btnPanel.add(cancelButton);
        panel.add(btnPanel);

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

        DBInsertPerson dbInsert = new DBInsertPerson(name,sqlDate,desc,imageFile);

        this.setVisible(false);
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
