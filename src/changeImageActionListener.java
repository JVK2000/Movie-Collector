import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class changeImageActionListener implements ActionListener {
    String movieFileName;

    public changeImageActionListener(String movieFileName) {
        this.movieFileName = movieFileName;
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("såsååsåws");
        JFrame jFrame = new JFrame();
        jFrame.setSize(250, 150);

        JLabel welcome = new JLabel("Please image url:");
        jFrame.add(welcome);
        JTextArea jTextField = new JTextArea();
        jFrame.add(jTextField);

        jFrame.setVisible(true);

    }



}
