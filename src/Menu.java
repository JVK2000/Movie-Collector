import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class Menu {

    JMenuBar menuBar = new JMenuBar();
    JMenu menu;
    JMenu submenu;

    public void createMenu() {
        menu = new JMenu("Menu");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription(
                "The only menu in this program that has menu items");
        menuBar.add(menu);
    }

    public void createMenu_refreshMovieList() {
        JMenuItem menuItem = new JMenuItem("Refresh movie list", KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.ALT_MASK));
        menuItem.addActionListener(new RecfechMovieListActionListener());
        menu.add(menuItem);
    }

    public void addSeparator() {
        menu.addSeparator();
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }

    public void CreateMenu_settings() {
        submenu = new JMenu("Settings");
        submenu.setMnemonic(KeyEvent.VK_S);
    }

    public void createMenu_settings_anItemInTheSubmenu() {
        JMenuItem menuItem = new JMenuItem("An item in the submenu");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, InputEvent.ALT_MASK));
        //menuItem.addActionListener(new ColumnSettingActionListener());
        submenu.add(menuItem);
        menuItem = new JMenuItem("Another item");
        submenu.add(menuItem);
    }

    public void createMenu_settings_numberOfColumns() {
        //a group of check box menu items
        menu.addSeparator();
        ButtonGroup group = new ButtonGroup();

        JMenu submenu2 = new JMenu("Number of colums");

        JRadioButtonMenuItem rbMenuItem = new JRadioButtonMenuItem("Low");
        rbMenuItem.addActionListener(new ColumnSettingActionListener(7));
        group.add(rbMenuItem);
        submenu2.add(rbMenuItem);

        rbMenuItem = new JRadioButtonMenuItem("Medium");
        rbMenuItem.addActionListener(new ColumnSettingActionListener(9));
        rbMenuItem.setSelected(true);
        group.add(rbMenuItem);
        submenu2.add(rbMenuItem);

        rbMenuItem = new JRadioButtonMenuItem("Max");
        rbMenuItem.addActionListener(new ColumnSettingActionListener(11));
        group.add(rbMenuItem);
        submenu2.add(rbMenuItem);

        submenu.add(submenu2);
    }


    public void addSubmenuToMenu() {
        menu.add(submenu);
    }


}
