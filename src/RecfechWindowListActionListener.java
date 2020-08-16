import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RecfechWindowListActionListener implements ActionListener {
    Window window;

    public RecfechWindowListActionListener(Window window) {
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
