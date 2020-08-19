

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
        JButton button;
        JPanel pane = new JPanel();
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        //if (shouldFill) {
            //natural height, maximum width
            c.fill = GridBagConstraints.HORIZONTAL;
        //}



        button = new JButton("Button 1");
       // if (shouldWeightX) {
            c.weightx = 0.5;
       // }
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        pane.add(button, c);

        button = new JButton("Button 2");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        pane.add(button, c);

        button = new JButton("Button 3");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 0;
        pane.add(button, c);


        button = new JButton("Button 4");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 3;
        c.gridy = 0;
        pane.add(button, c);

        button = new JButton("Long-Named Button 4");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 40;      //make this component tall
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;

        pane.add(button, c);

        button = new JButton("5");
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
