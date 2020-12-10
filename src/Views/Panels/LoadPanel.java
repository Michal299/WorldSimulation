package Views.Panels;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class LoadPanel extends JDialog {
    
    private static final long serialVersionUID = 8550758752940670748L;
    private String path;

    public LoadPanel() {
        String message = "Podaj nazwe pliku z zapisem (nie zapomnij o rozszerzeniu)";
        path = (String)JOptionPane.showInputDialog(
            this,
            message,
            "Wczytywanie świata",
            JOptionPane.PLAIN_MESSAGE,
            null,
            null,
            "save.txt");
    }

    public String getPath() {
        return path;
    }
}