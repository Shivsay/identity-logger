package peopleproject.screens;

import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.ListCellRenderer;
import javax.swing.JList;

import peopleproject.util.People;

public class ListRenderer extends JLabel implements ListCellRenderer<People> {

    public ListRenderer() {
        this.setOpaque(true);
    }


    @Override
    public Component getListCellRendererComponent(JList<? extends People> list, People people, int index, boolean isSelected, boolean cellHasFocus) {
        this.setText((index+1)+". "+people.toString());
        
        //since JLabel is opaque it just shows grey
        if (isSelected) {
             setBackground(list.getSelectionBackground());
             setForeground(list.getSelectionForeground());
         } else {
             setBackground(list.getBackground());
             setForeground(list.getForeground());
         }

        return this;
    }
}

