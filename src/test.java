import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;

class NativeOpenDialogDemo {
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
}