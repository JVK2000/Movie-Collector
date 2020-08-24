package functionality;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class RoundTextField extends JTextField implements KeyListener {

    public RoundTextField(String text, int columns) {
        super(text, columns);
        setOpaque(false);
        setBorder(new RoundBorder());

        // Moves the text a couple of dots to the right
        Border line = BorderFactory.createLineBorder(Color.DARK_GRAY);
        Border empty = new EmptyBorder(0, 20, 0, 0);
        CompoundBorder border = new CompoundBorder(line, empty);
        setBorder(border);

        setBackground(Color.WHITE);
        Font font = new Font("SansSerif", Font.BOLD, 25);
        setFont(font);
        setPreferredSize(new Dimension(400, 40));

        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked (java.awt.event.MouseEvent evt){
                //setText("   ");
                setText(null);

            }
        });

        addKeyListener(this);
    }




    public void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth()/4 - 1, getHeight() - 1,
                55, 55);
        super.paintComponent(g);
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        Frame.hideNonMatchingComponents(getText());
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            System.out.println(getText());
        }
    }
}

class RoundBorder extends AbstractBorder
{
    public void paintBorder(Component c, Graphics g,
                            int x, int y,
                            int width, int height) {
        Color oldColor = g.getColor();

        g.setColor(Color.black);
        g.drawRoundRect(x, y, width/4 - 1, height - 1,
                55, 55);

        g.setColor(oldColor);
    }

    public Insets getBorderInsets(Component c) {
        return new Insets(4, 4, 4, 4);
    }

    public Insets getBorderInsets(Component c, Insets insets) {
        insets.left = insets.top = insets.right = insets.bottom = 4;
        return insets;
    }

}
