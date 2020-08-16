package actionListener;

import functionality.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RefreshWindowListActionListener implements ActionListener {
    final Window window;

    public RefreshWindowListActionListener(Window window) {
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            window.restartApplication();
        } catch (Exception exception) {
            exception.printStackTrace();
        }


    }
}
