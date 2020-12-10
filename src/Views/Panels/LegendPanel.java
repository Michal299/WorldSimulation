package Views.Panels;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

public class LegendPanel extends JDialog {


    private static final long serialVersionUID = -7184826054933954734L;

    private String[] names = {
        "Wilk", 
        "Owca", 
        "Lis", 
        "Antylopa", 
        "Żółw", 
        "Cyber owca",
        "Człowiek",

        "Trawa", 
        "Mlecz", 
        "Guarana", 
        "Wilcze jagody", 
        "Barszcz sosnowskiego"
    };

    private Color[] colors = {
        Color.DARK_GRAY, //Wolf
        new ColorUIResource(232, 230, 220), //Sheep
        new ColorUIResource(247, 136, 0), //Fox
        new ColorUIResource(217, 160, 91), //Antelope
        new ColorUIResource(23, 107, 20), //Turtle
        new ColorUIResource(135, 135, 112), //Cyber Sheep
        new ColorUIResource(235, 171, 136), //Human

        new ColorUIResource(4, 255, 0), //Grass
        new ColorUIResource(252, 255, 36), //SowThistle
        new ColorUIResource(255, 36, 36), //Guarana
        new ColorUIResource(119, 0, 255), //Belladonna
        new ColorUIResource(173, 66, 89) //SosnowskyHogweed
    }; 
    
    public LegendPanel() {
        super();
        var size = getSize();
        size.width = 200;
        size.height = 400;
        setSize(size);

        var layout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
        setLayout(layout);

        getContentPane().setBackground(new ColorUIResource(173, 181, 189));
        
        for(int i = 0; i < names.length; i++) {
            
            JLabel label = new JLabel(names[i]);
            label.setForeground(colors[i]);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setFont(new FontUIResource("Arial", Font.BOLD, 15));
            add(label);
        }
        
        pack();
        setVisible(true);
    }
}