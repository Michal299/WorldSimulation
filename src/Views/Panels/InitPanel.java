package Views.Panels;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controllers.HexInit;
import Controllers.SquaredInit;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;

public class InitPanel extends JPanel {

    private static final long serialVersionUID = 3044543929202870185L;
    private final JLabel widthLabel;
    private final JLabel heightLabel;

    private final JTextField widthField;
    private final JTextField heightField;

    private final JButton hexBtn;
    private final JButton squaredBtn;

    public InitPanel() {

        var size = getPreferredSize();
        size.width = 300;
        size.height = 100;
        setPreferredSize(size);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        setBorder(BorderFactory.createTitledBorder("Konfigurowanie świata"));

        widthLabel = new JLabel("Podaj szerokość: ");
        heightLabel = new JLabel("Podaj wysokość: ");

        widthField = new JTextField(8);
        heightField = new JTextField(8);

        hexBtn = new JButton("Hex");
        hexBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                var c = new HexInit(widthField.getText(), heightField.getText());
                if(c.isError()) {
                    
                    String text = "Podano złe wartośći!";
                    setBorder(BorderFactory.createTitledBorder(text));

                }
            }
            
        });

        squaredBtn = new JButton("Squared");
        squaredBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                var c = new SquaredInit(widthField.getText(), heightField.getText());
                if(c.isError()) {
                    
                    String text = "Podano złe wartośći!";
                    setBorder(BorderFactory.createTitledBorder(text));

                }
            }
            
        });

        //// First column /////////////////////
        gbc.anchor = GridBagConstraints.LINE_END;

        gbc.weightx = 0.5;
        gbc.weighty = 0.5;

        gbc.gridx = 0;
        gbc.gridy = 0;
        
        add(widthLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(heightLabel, gbc);

        //// Second column
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(widthField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(heightField, gbc);

        // Final row
        gbc.weighty = 10;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(hexBtn, gbc);
        
        gbc.anchor = GridBagConstraints.FIRST_LINE_END;
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(squaredBtn, gbc);

    }
}