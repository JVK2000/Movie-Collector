package functionality;

import javax.swing.*;
import java.awt.*;

public class SearchComponent {
    JTextField textField;

    public JPanel createSearchComponent() {
        JPanel searchPanel = new JPanel();
        searchPanel.setBackground(Color.DARK_GRAY);
        searchPanel.setLayout(new BorderLayout());
        searchPanel.add(new RoundTextField("Search for a movie...", 1));
        return searchPanel;
    }

}

