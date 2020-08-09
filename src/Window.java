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

    public void addLoadScreen(String gifPath, int gifWith, int gifHeight){
        startPanel = new JPanel();
        startPanel.setBackground(Color.DARK_GRAY);
        startPanel.setForeground(Color.DARK_GRAY);
        ImageIcon loading = new ImageIcon(gifPath);
        loading.setImage(loading.getImage().getScaledInstance(gifWith, gifHeight, Image.SCALE_DEFAULT));
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

    public void makeScrolable(JPanel jPanel) {
        JScrollPane scrPane = new JScrollPane(jPanel);
        scrPane.getVerticalScrollBar().setUnitIncrement(20);
        frame.add(scrPane);
        frame.setVisible(true);

    }

    public void restartApplication() throws Exception {
        frame.setVisible(false);
        Frame.createWindow2();
    }

    public void removeLoadScreen(){
        frame.remove(startPanel);
    }

    public void createMenu(Window window) throws Exception {
        Menu menu = new Menu(window);
        menu.createMenu();
        menu.createMenu_refreshMovieList();
        menu.addSeparator();
        menu.CreateMenu_settings();
        menu.createMenu_settings_movieIconSize();
        menu.createMenu_settings_numberOfColumns();
        menu.addSubmenuToMenu();
        frame.setJMenuBar(menu.getMenuBar());
    }

}
