import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
/**
 * A custom JTextField with rounded corners.
 */
class RoundTextField extends JTextField {

    public RoundTextField(String text, int columns) {
        super(text, columns);
        setOpaque(false);
        setBorder(new RoundBorder());
    }

    public void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1,
                20, 20);
        super.paintComponent(g);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("RoundTextField");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new FlowLayout());

        contentPane.add(new RoundTextField("One", 60));
        contentPane.add(new RoundTextField("Two", 20));
        contentPane.add(new RoundTextField("Three", 20));

        frame.setVisible(true);
    }

}

class RoundBorder extends AbstractBorder
{
    public void paintBorder(Component c, Graphics g,
                            int x, int y,
                            int width, int height) {
        Color oldColor = g.getColor();

        g.setColor(Color.black);
        g.drawRoundRect(x, y, width - 1, height - 1,
                20, 20);

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

/*
class Test {
    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setVisible(true);


        JPanel panel = new JPanel();
        panel.setLayout(new WrapLayout(0));

        JScrollPane pane = new JScrollPane(panel);
        frame.add(pane, BorderLayout.CENTER);

        for (int i = 0; i < 80; i++) {
            panel.add(new JLabel("Label" + i));
        }
    }
}

/*
class Test implements Runnable
{
    private String[] keys = {"One", "Twoooooo", "Three", "Four",
            "Five", "Six", "Seven", "Eight",
            "Nine", "Ten", "Eleven", "Twelve"};
    private String[] values = {"Apple", "Boy", "Cat", "Denmark",
            "Elephant", "Foo", "Hello", "Igloo",
            "Jug", "Kangaroo", "Lip", "Now"};

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Test());
    }

    public void run()
    {
        JPanel panel = new JPanel(new FlowLayout());
        GridBagConstraints gbc;
        JTextField textField = null;
        int maxWidth = 0;
        JLabel[] labels = new JLabel[keys.length];

        for (int i = 0; i < keys.length; i++)
        {
            labels[i] = new JLabel(keys[i]);
            maxWidth = Math.max(labels[i].getPreferredSize().width, maxWidth);
        }

        JPanel[] panels = new JPanel[keys.length];


        for (int i = 0; i < keys.length; i++)
        {
            panels[i] = new JPanel(new GridBagLayout());

            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.EAST;
            gbc.insets = new Insets(1,1,1,1);
            panels[i].add(Box.createVerticalStrut(maxWidth), gbc);

            gbc.gridy = 1;
            panels[i].add(labels[i], gbc);

            textField = new JTextField(10);
            textField.setText(values[i]);
            gbc.gridx = 1;
            panels[i].add(textField, gbc);

            panel.add(panels[i]);
        }

        JFrame frame = new JFrame("Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel);
        frame.setSize(240, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}


        /*
        MButton button;
        JPanel pane = new JPanel();
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        //if (shouldFill) {
            //natural height, maximum width
            c.fill = GridBagConstraints.HORIZONTAL;
        //}



        button = new MButton("Button 1");
       // if (shouldWeightX) {
            c.weightx = 0.5;
       // }
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        pane.add(button, c);

        button = new MButton("Button 2");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        pane.add(button, c);

        button = new MButton("Button 3");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 0;
        pane.add(button, c);


        button = new MButton("Button 4");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 3;
        c.gridy = 0;
        pane.add(button, c);

        button = new MButton("Long-Named Button 4");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 40;      //make this component tall
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;

        pane.add(button, c);

        button = new MButton("5");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;       //reset to default
        c.weighty = 1.0;   //request any extra vertical space
        c.anchor = GridBagConstraints.PAGE_END; //bottom of space
        c.insets = new Insets(10,0,0,0);  //top padding
        c.gridx = 1;       //aligned with button 2
        c.gridwidth = 2;   //2 columns wide
        c.gridy = 2;       //third row
        pane.add(button, c);
        JFrame jFrame = new JFrame();
        jFrame.add(pane);
        jFrame.setSize(500, 500);
        jFrame.setVisible(true);


        }

         */





    /*
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    System.out.println(ex);
                }
                Frame frame = new Frame();
                JFileChooser chooser = new JFileChooser();
                FileFilter imageFilter = new FileNameExtensionFilter(
                        "Image files", ImageIO.getReaderFileSuffixes());
                chooser.addChoosableFileFilter(imageFilter);
                UIManager.put("Panel.background", Color.DARK_GRAY);
                UIManager.put("control", Color.DARK_GRAY);


                if (chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                    // do something
                }

            }
        });
    }

     */
