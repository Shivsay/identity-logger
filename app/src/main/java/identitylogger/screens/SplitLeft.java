package identitylogger.screens;

import java.util.Vector;
import javax.swing.JList;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

import identitylogger.util.People;
import identitylogger.database.DBGetPeople;


public class SplitLeft extends JList<People> {
    Vector<People> currentListItems;
    SplitRight right;

    public SplitLeft(SplitRight right) {
        super();
        this.right = right;
        updateList();
        this.setListData(currentListItems);
        this.setCellRenderer(new ListRenderer());

        this.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    right.updatePerson(currentListItems.get(getSelectedIndex()).getDBID());
                }
            }
        });

    }



    public void updateList() {
        DBGetPeople dbGetPeople = new DBGetPeople();
        this. currentListItems = dbGetPeople.updatePeopleList(); 
        this.setListData(currentListItems);
        return ;
    }

}

