package Views.Panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import javax.swing.plaf.ColorUIResource;

import Controllers.LoadController;
import Controllers.NextRoundController;
import Controllers.PrevBoardController;
import Controllers.SaveController;

public class MenuPanel extends JPanel {

    private static final long serialVersionUID = -1274667802331789327L;
    private final JButton nextRoundButton;
    private final JButton switchButton;
    private final JButton saveButton;
    private final JButton loadButton;
    private final JButton legendButton;
    private final JButton exitButton;

    public MenuPanel() {
        Dimension size = getPreferredSize();
        size.height = 50;
        size.width = 1000;
        setPreferredSize(size);
        setBackground(new ColorUIResource(38, 70, 83));
        setVisible(true);

        setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        
        nextRoundButton = new JButton("Następna runda");
        nextRoundButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
                new NextRoundController();
			}
            
        });

        switchButton = new JButton("Poprzednia plansza");
        switchButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new PrevBoardController();
                if(switchButton.getForeground() == Color.RED) {
                    switchButton.setForeground(Color.BLACK);
                }
                else {
                    switchButton.setForeground(Color.RED);
                }
            }

        });
        
        saveButton = new JButton("Zapisz");
        saveButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new SaveController();
            }

        });

        loadButton = new JButton("Wczytaj");
        loadButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new LoadController();

            }

        });

        legendButton = new JButton("Legenda");
        legendButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new LegendPanel();
            }

        });
        
        exitButton = new JButton("Wyjście");
        exitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }

        });

        setLayout(new FlowLayout(FlowLayout.CENTER, 50, 10));
        add(nextRoundButton);
        add(switchButton);
        add(saveButton);
        add(loadButton);
        add(legendButton);
        add(exitButton);

        setDefaultButtonEnabled();
    }

    public void setDefaultButtonEnabled() {
        nextRoundButton.setEnabled(false);
        switchButton.setEnabled(false);
        saveButton.setEnabled(false);
        legendButton.setEnabled(false);
    }

    public void activateButtons() {
        nextRoundButton.setEnabled(true);
        switchButton.setEnabled(true);
        saveButton.setEnabled(true);
        legendButton.setEnabled(true);
    }

}   