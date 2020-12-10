package Views.Boards;

import javax.swing.JPanel;

import Controllers.AddOrganismController;
import Controllers.SuperPowerController;
import Models.Utilities.Point;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;

import Views.Fields.Field;

public abstract class Board extends JPanel implements MouseListener, KeyListener {
    
    private static final long serialVersionUID = 6711681598804471798L;
    
    protected Field[][] fields;
    protected Color[][] presentColors;
    protected Color[][] previousColors;
    protected Color[][] current;

    protected final int xCells;
    protected final int yCells;
    protected int fieldSize;

    public Board(int xCells, int yCells, int fieldSize) {
        this.xCells = xCells;
        this.yCells = yCells;
        this.fieldSize = fieldSize;

        fields = new Field[xCells][yCells];
        presentColors = new Color[xCells][yCells];
        previousColors = new Color[xCells][yCells];
        current = presentColors;
        generateBoard();

        addMouseListener(this);
        addKeyListener(this);
    }

    public void updateBoard(Color[][] newColors) {
        if(current == presentColors) {
            current = newColors;
        }
        else if(current == previousColors) {
            current = presentColors;
        }

        previousColors = presentColors;
        presentColors = newColors;
    }

    public void switchCurrentsBoards() {
        if(current == previousColors) {
            current = presentColors;
        }
        else if(current == presentColors) {
            current = previousColors;
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        var g2d = (Graphics2D) g;
        var color = g2d.getColor();

        for(int x = 0; x < xCells; x++) {
            for(int y = 0; y < yCells; y++) {
                if(current[x][y] != null) {
                    g2d.setColor(current[x][y]);
                }
                else {
                    g2d.setColor(fields[x][y].getColor());
                }
                g2d.fill(fields[x][y].getBody());
            }
        }
        requestFocus();
        g2d.setColor(color);
    }

    protected abstract void generateBoard();

    @Override
    public void mouseReleased(MouseEvent event) {
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        var point = clickedPoint(e.getX(), e.getY());
        if(point != null)
        {
            new AddOrganismController(point);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        

    }
    @Override
    public void keyPressed(KeyEvent e) {
        
        if(e.getKeyCode() == KeyEvent.VK_S) {
            
            new SuperPowerController();
        }
        
    }

    /**
     * 
     * @return field coordinates which one have been clicked or null when 
     * click was done outside the board
     */
    private Point clickedPoint(int x, int y) {
        
        for(int xCoord = 0; xCoord < xCells; xCoord++)
        {
            for(int yCoord = 0; yCoord < yCells; yCoord++ )
            {
                if(fields[xCoord][yCoord].getBody().contains(x, y))
                {
                    return new Point(xCoord, yCoord);
                }
            }
        }
        return null;
    }
}