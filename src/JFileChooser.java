import javax.swing.*;
import java.awt.*;

class DemoJFileChooser extends Component {
    JButton go;
    JFileChooser chooser;
    String choosertitle;

    public DemoJFileChooser() {
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle(choosertitle);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        //
        // disable the "All files" option.
        //
        chooser.setAcceptAllFileFilterUsed(false);
        //
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            System.out.println("getCurrentDirectory(): "
                    +  chooser.getCurrentDirectory());
            System.out.println("getSelectedFile() : "
                    +  chooser.getSelectedFile());
        }
        else {
            System.out.println("No Selection ");
        }
    }

    public Dimension getPreferredSize(){
        return new Dimension(200, 200);
    }

}