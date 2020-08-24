package functionality;

import javax.swing.*;


/**
 * Add the ability to save the movies path in the JButton object, now called MButton
 */
public class MButton extends JButton {
    private String moviePath;

    public MButton(String s, ImageIcon icon) {
        super(s, icon);
    }

    public MButton(String s) {
        super(s);
    }

    public String getMoviePath() {
        return moviePath;
    }

    public void setMoviePath(String moviePath) {
        this.moviePath = moviePath;
    }
}
