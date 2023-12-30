package peopleproject.screens;

import javax.swing.JSplitPane;
import javax.swing.JPanel;
import javax.swing.JList;
import java.awt.*;

import peopleproject.util.People;

public class MainSplitPane extends JSplitPane {
    public MainSplitPane(JList<People> left,JPanel right) {            
        super(JSplitPane.HORIZONTAL_SPLIT, left, right);
        super.setDividerSize(4);
        super.setResizeWeight(0.1);
    }

}
