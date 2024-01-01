package identitylogger.screens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import identitylogger.screens.InsertForm;

public class ControlPane extends JPanel implements ActionListener{
    SplitLeft list;
    public JButton insertBtn, refreshBtn;

    public ControlPane(SplitLeft list) {
        this.list = list;
        //this.itemInsertFrame = itemInsertFrame;
        createBtns();
    }

    private void createBtns() {
        super.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        super.setBorder(new EmptyBorder(10, 10, 10, 10));
        JButton insertBtn = new JButton("Insert");
        this.insertBtn = insertBtn;
        JButton refreshBtn = new JButton("Refresh");
        this.refreshBtn = refreshBtn;
        super.add(insertBtn);
        super.add(refreshBtn);
        insertBtn.addActionListener(this);
        refreshBtn.addActionListener(this);
    }

    public void actionPerformed(ActionEvent event) {

        if (event.getActionCommand() == "Insert") {
            insertBtn.setEnabled(false);
            InsertForm itemInsertFrame = new InsertForm(list, this);
            //insertBtn.setEnabled(true);
        }

        else if (event.getActionCommand() == "Refresh") {
            list.updateList();
        }
    }
}
