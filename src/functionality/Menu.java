package functionality;

import actionListener.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Menu {
    private static final int PIXELS_BTW_MOVIE_ICONS = 425;
    final JMenuBar menuBar = new JMenuBar();
    JMenu menu, submenu, columnSubmenu;
    private ButtonGroup group;
    Window window;

    public Menu(Window window) {
        this.window = window;
    }
    
    public void createMenuItems() throws Exception {
        createMenu();
        createMenu_refreshWindow();
        createMenu_refreshMovieList();
        addSeparator();
        createMenu_selectMovieFolder();
        createMenu_settings();
        createMenu_settings_movieIconSize();
        createMenu_settings_numberOfColumns();
        addSubmenuToMenu();
    }

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
        menuItem.addActionListener(new RefreshMovieListActionListener());
        menu.add(menuItem);
    }

    public void createMenu_refreshWindow() {
        JMenuItem menuItem = new JMenuItem("Refresh Functionality.Window", KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke((char) KeyEvent.VK_1));
        menuItem.addActionListener(new RefreshWindowListActionListener(window));
        menu.add(menuItem);
    }

    public void addSeparator() {
        menu.addSeparator();
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }

    public void createMenu_settings() {
        submenu = new JMenu("Settings");
        submenu.setMnemonic(KeyEvent.VK_S);
    }

    public void createMenu_settings_movieIconSize() throws Exception {
        group = new ButtonGroup();
        columnSubmenu = new JMenu("Movie Icon Size");
        addMovieIconSizeOption("Extremely Small", 0.4);
        addMovieIconSizeOption("Very small", 0.6);
        addMovieIconSizeOption("Small", 0.8);
        addMovieIconSizeOption("Medium", 1);
        addMovieIconSizeOption("Large", 1.2);
        addMovieIconSizeOption("Very Large", 1.4);
        addMovieIconSizeOption("Extremely Large", 1.6);
        submenu.add(columnSubmenu);
    }

    public void addMovieIconSizeOption(String optionName, double scale) throws Exception {
        SettingManager settingManager = new SettingManager();
        double selectedScale = (double) settingManager.getMovieDimensionHeight()/400;

        JRadioButtonMenuItem rbMenuItem = new JRadioButtonMenuItem(optionName);
        rbMenuItem.addActionListener(new MovieIconSizeSettingActionListener(scale, window));
        if (selectedScale == scale) rbMenuItem.setSelected(true);
        group.add(rbMenuItem);
        columnSubmenu.add(rbMenuItem);
    }

    public void createMenu_settings_numberOfColumns() throws Exception {
        //a group of check box menu items
        menu.addSeparator();
        group = new ButtonGroup();
        columnSubmenu = new JMenu("Number of columns");
        addNumberOfColumnsOption("5", 5);
        addNumberOfColumnsOption("6", 6);
        addNumberOfColumnsOption("7", 7);
        addNumberOfColumnsOption("8", 8);
        addNumberOfColumnsOption("9", 9);
        addNumberOfColumnsOption("10", 10);
        addNumberOfColumnsOption("11", 11);
        addNumberOfColumnsOption("Auto", 0);
        submenu.add(columnSubmenu);
    }

    public void addNumberOfColumnsOption(String optionName, int Columns) throws Exception {
        JRadioButtonMenuItem rbMenuItem = new JRadioButtonMenuItem(optionName);
        if (Columns == 0) {
            GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
            float width = gd.getDisplayMode().getWidth();
            Columns = (int) (width / PIXELS_BTW_MOVIE_ICONS);
            rbMenuItem.setSelected(true);
        } else {
            SettingManager settingManager = new SettingManager();
            Long selectedNumbOfColumns = settingManager.getColumns();
            if (Math.toIntExact(selectedNumbOfColumns) == Columns) rbMenuItem.setSelected(true);
        }
        rbMenuItem.addActionListener(new ColumnSettingActionListener(Columns, window));
        group.add(rbMenuItem);
        columnSubmenu.add(rbMenuItem);
    }


    public void addSubmenuToMenu() {
        menu.add(submenu);
    }


    public void createMenu_selectMovieFolder() {
        JMenuItem menuItem = new JMenuItem("Select Movie Folder", KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke((char) KeyEvent.VK_1));
        menuItem.addActionListener(new SelectMovieFolderActionListener());
        menu.add(menuItem);
    }
}
