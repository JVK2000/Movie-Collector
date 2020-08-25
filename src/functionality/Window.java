package functionality;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame{
    private JPanel startPanel;

    public Window(String frameTitle){
        super(frameTitle);
    }

    public void addLoadScreen(String gifPath, int gifWidth, int gifHeight){
        startPanel = new JPanel();
        startPanel.setBackground(Color.DARK_GRAY);
        ImageIcon loading = new ImageIcon(gifPath);
        loading.setImage(loading.getImage().getScaledInstance(gifWidth, gifHeight, Image.SCALE_DEFAULT));
        startPanel.setLayout(new GridBagLayout());
        startPanel.add(new JLabel(loading, JLabel.CENTER));
        add(startPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void setIconImage(String iconPath) {
        Image applicationIcon = Toolkit.getDefaultToolkit().getImage(iconPath);
        setIconImage(applicationIcon);
    }

    public void makeScrollable(JPanel panel) {
        JScrollPane scrPane = new JScrollPane(panel);
        scrPane.getVerticalScrollBar().setUnitIncrement(20);
        add(scrPane);
        setVisible(true);
    }

    public void restartApplication() throws Exception {
        dispose();
        //Frame.main(null);
    }

    public void removeLoadScreen(){
        remove(startPanel);
    }


    public void setFullScreenBorderless() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
    }

    public void setFullScreen() {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();
        setSize(width, height);
    }

    public void add(JScrollPane pane) {
        add(pane, BorderLayout.CENTER);

    }
}
