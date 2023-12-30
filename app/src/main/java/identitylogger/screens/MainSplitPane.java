package identitylogger.screens;

import javax.swing.JSplitPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.*;

import identitylogger.util.People;
import identitylogger.screens.SplitLeft;

public class MainSplitPane extends JSplitPane {
    public MainSplitPane(JScrollPane leftScrollPane/*JList<People> left*/,SplitRight right) {
        super(JSplitPane.HORIZONTAL_SPLIT, leftScrollPane, right);
        leftScrollPane.setMinimumSize(new Dimension(100, 50));

        super.setDividerSize(4);
        super.setResizeWeight(0.1);
    }

}
