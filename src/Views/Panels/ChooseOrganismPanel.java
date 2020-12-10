package Views.Panels;

import javax.swing.JDialog;

import javax.swing.JOptionPane;


public class ChooseOrganismPanel extends JDialog {

    private static final long serialVersionUID = 1716333370439663511L;

    private final String[] options = { "Wilk", "Owca", "Lis", "Cyber owca", "Żółw",
            "Antylopa",
    "Guarana", "Trawa", "Wilcze jagody", "Mlecz", "Barszcz sosnowskiego"};

    private final String result;
    
    public ChooseOrganismPanel() {
        
        String message = "Wybierz organizm który chcesz dodać.";
        result = (String)JOptionPane.showInputDialog( this,
            message, "Dodawanie organizmu", JOptionPane.PLAIN_MESSAGE, null, options, "Wilk");

    }

    public String getResult() {
        return result;
    }
}