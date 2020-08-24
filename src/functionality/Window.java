package functionality;

import javax.swing.*;
import java.awt.*;

public class Window {
    private JFrame frame;
    private JPanel startPanel;

    public void setTitle(String frameTitle) {
        frame = new JFrame(frameTitle);
    }

    public void setSize(int width, int height) {
        frame.setSize(width, height);
    }

    public Dimension getSize() {
        return frame.getSize();
    }

    public void addLoadScreen(String gifPath, int gifWidth, int gifHeight){
        startPanel = new JPanel();
        startPanel.setBackground(Color.DARK_GRAY);
        //startPanel.setForeground(Color.DARK_GRAY);
        ImageIcon loading = new ImageIcon(gifPath);
        loading.setImage(loading.getImage().getScaledInstance(gifWidth, gifHeight, Image.SCALE_DEFAULT));
        startPanel.setLayout(new GridBagLayout());
        startPanel.add(new JLabel(loading, JLabel.CENTER));
        frame.add(startPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void setJMenuBar(JMenuBar jMenuBar) {
        frame.setJMenuBar(jMenuBar);
    }

    public void setIconImage(String iconPath) {
        Image applicationIcon = Toolkit.getDefaultToolkit().getImage(iconPath);
        frame.setIconImage(applicationIcon);
    }

    public void setVisible() {
        frame.setVisible(true);
    }

    public void makeScrollable(JPanel panel) {
        JScrollPane scrPane = new JScrollPane(panel);
        scrPane.getVerticalScrollBar().setUnitIncrement(20);
        frame.add(scrPane);
        frame.setVisible(true);
    }


    public void restartApplication() throws Exception {
        frame.dispose();
        Frame.main(null);
    }

    public void removeLoadScreen(){
        frame.remove(startPanel);
    }

    public void createMenu(Window window) throws Exception {
        Menu menu = new functionality.Menu(window);
        menu.createMenu();
        menu.createMenu_refreshWindow();
        menu.createMenu_refreshMovieList();
        menu.addSeparator();
        menu.createMenu_selectMovieFolder();
        menu.createMenu_settings();
        menu.createMenu_settings_movieIconSize();
        menu.createMenu_settings_numberOfColumns();
        menu.addSubmenuToMenu();
        frame.setJMenuBar(menu.getMenuBar());
    }

    public void setFullScreenBorderless() {
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
    }

    public void setFullScreen() {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();
        setSize(width, height);
    }

    public void add(JScrollPane pane) {
        frame.add(pane, BorderLayout.CENTER);

    }
}
