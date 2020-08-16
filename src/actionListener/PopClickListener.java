package actionListener;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Code borrowed from https://stackoverflow.com/questions/766956/how-do-i-create-a-right-click-context-menu-in-java-swing
 */
public class PopClickListener extends MouseAdapter {
    final String movieFileName;

    public PopClickListener(String movieFileName) {
        this.movieFileName = movieFileName;
    }

    public void mousePressed(MouseEvent e) {
        if (e.isPopupTrigger())
            doPop(e);
    }

    public void mouseReleased(MouseEvent e) {
        if (e.isPopupTrigger())
            doPop(e);
    }

    private void doPop(MouseEvent e) {
        PopUpDemo menu = new PopUpDemo(movieFileName);
        menu.show(e.getComponent(), e.getX(), e.getY());
    }
}

class PopUpDemo extends JPopupMenu {
    JMenuItem anItem;
    public PopUpDemo(String movieFileName) {
        anItem = new JMenuItem("Change Image | url");
        anItem.addActionListener(new changeImageURLActionListener(movieFileName));
        add(anItem);
        anItem = new JMenuItem("Change Image | saved file ");
        anItem.addActionListener(new changeImageSavedFileActionListener(movieFileName));
        add(anItem);
    }
}
