import javax.swing.*;
import java.awt.event.KeyEvent;

public class Menu {

    final JMenuBar menuBar = new JMenuBar();
    JMenu menu;
    JMenu submenu, columnSubmenu;
    private ButtonGroup group;


    public void createMenu() {
        menu = new JMenu("Menu");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription(
                "The only menu in this program that has menu items");
        menuBar.add(menu);
    }

    public void createMenu_refreshMovieList() {
        JMenuItem menuItem = new JMenuItem("Refresh movie list", KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke((char) KeyEvent.VK_1));
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

    public void createMenu_settings_movieIconSize() throws Exception {
        group = new ButtonGroup();
        columnSubmenu = new JMenu("Number of colums");
        addMovieIconSizeOption("Small", 0.8);
        addMovieIconSizeOption("Medium", 1);
        addMovieIconSizeOption("Large", 1.2);

        submenu.add(columnSubmenu);
    }

    public void addMovieIconSizeOption(String optionName, double scale) throws Exception {
        SettingManager settingManager = new SettingManager();
        Long selectedNumbOfColumns = settingManager.getColumns();

        JRadioButtonMenuItem rbMenuItem = new JRadioButtonMenuItem(optionName);
        rbMenuItem.addActionListener(new MovieIconSizeSettingActionListener(scale));

        //if (Math.toIntExact(selectedNumbOfColumns) == Columns) rbMenuItem.setSelected(true);
        group.add(rbMenuItem);
        columnSubmenu.add(rbMenuItem);
    }

    public void createMenu_settings_numberOfColumns() throws Exception {
        //a group of check box menu items
        menu.addSeparator();
        group = new ButtonGroup();
        columnSubmenu = new JMenu("Number of colums");
        addNumberOfColumnsOption("5", 5);
        addNumberOfColumnsOption("6", 6);
        addNumberOfColumnsOption("7", 7);
        addNumberOfColumnsOption("8", 8);
        addNumberOfColumnsOption("9", 9);
        addNumberOfColumnsOption("10", 10);
        addNumberOfColumnsOption("11", 11);

        submenu.add(columnSubmenu);
    }

    public void addNumberOfColumnsOption(String optionName, int Columns) throws Exception {
        SettingManager settingManager = new SettingManager();
        Long selectedNumbOfColumns = settingManager.getColumns();

        JRadioButtonMenuItem rbMenuItem = new JRadioButtonMenuItem(optionName);
        rbMenuItem.addActionListener(new ColumnSettingActionListener(Columns));
        //if (Math.toIntExact(selectedNumbOfColumns) == Columns) rbMenuItem.setSelected(true);
        group.add(rbMenuItem);
        columnSubmenu.add(rbMenuItem);
    }


    public void addSubmenuToMenu() {
        menu.add(submenu);
    }


}
