package Views;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Views.Boards.Board;
import Views.Panels.InitPanel;
import Views.Panels.MenuPanel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class MainFrame extends JFrame {
    
    private static final long serialVersionUID = 8770576037258143635L;
    private final MenuPanel menuPanel;
    private final InitPanel initPanel;

    private final JTextArea commentsArea;
    private final JScrollPane scroll;

    private Board board;

    private final GridBagConstraints gbc;
    
    public MainFrame() {
        super("Michał Błajet 180564");
        setSize(1280, 840);
        
        menuPanel = new MenuPanel();
        initPanel = new InitPanel();

        commentsArea = new JTextArea(10, 30);
        commentsArea.setEditable(false);
        commentsArea.setVisible(false);
        commentsArea.setWrapStyleWord(true);

        scroll = new JScrollPane(commentsArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVisible(false);


        board = null;

        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.0001;
        gbc.weighty = 0.0001;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(menuPanel, gbc);

        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(initPanel, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.weightx = 0.001;
        gbc.weighty = 0.001;
        add(scroll, gbc);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void removeInit() {
        remove(initPanel);
    }

    public void setBoard(Board obj) {
        
        if(board != null) {
            remove(board);
        }

        board = obj;
        remove(initPanel);
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.1;
        gbc.weighty = 0.1;
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        
        add(board, gbc);

        commentsArea.setVisible(true);
        scroll.setVisible(true);
        repaint();
    }

    public Board getBoard() {
        return board;
    }

    public MenuPanel getMenuPanel() {
        return menuPanel;
    }

    public JTextArea getCommentsArea() {
        return commentsArea;
    }
}