package Views.Boards;

import Models.Utilities.Point;
import Views.Fields.SquaredField;
import java.awt.event.KeyEvent;

import Controllers.HumanMoveController;

public class SquaredBoard extends Board {

    private static final long serialVersionUID = 800048146416751683L;

    public SquaredBoard(int xCells, int yCells, int fieldSize) {
        super(xCells, yCells, fieldSize);
    }

    @Override
    protected void generateBoard() {
        
        Point startingPoint = new Point(10, 10);

        for (int x = 0; x < xCells; x++) {
            for (int y = 0; y < yCells; y++) {
                fields[x][y] = new SquaredField(fieldSize, (Point) startingPoint.clone());
                startingPoint.translate(0, fieldSize + 3);
            }
            startingPoint.translate(fieldSize + 3, -(yCells * (fieldSize + 3)));
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        if(e.getKeyCode() == KeyEvent.VK_UP
        || e.getKeyCode() == KeyEvent.VK_DOWN
        || e.getKeyCode() == KeyEvent.VK_RIGHT
        || e.getKeyCode() == KeyEvent.VK_LEFT) {
            new HumanMoveController(e.getKeyCode());
        }
        else {
            super.keyPressed(e);
        }

    }

    
    
}