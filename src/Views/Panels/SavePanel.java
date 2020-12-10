package Views.Panels;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class SavePanel extends JDialog {
    
    private static final long serialVersionUID = 2944539868890899500L;
    private String path;

    public SavePanel() {
        String message = "Podaj nazwe pliku w którym ma zostać zapisany świat (nie zapomnij o rozszerzeniu)";
        path = (String)JOptionPane.showInputDialog(
            this,
            message,
            "Zapis stanu",
            JOptionPane.PLAIN_MESSAGE,
            null,
            null,
            "save.txt");
    }

    public String getPath() {
        return path;
    }
}