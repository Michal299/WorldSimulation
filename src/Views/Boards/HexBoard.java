package Views.Boards;

import Controllers.HumanMoveController;
import Models.Utilities.Point;
import Views.Fields.HexField;
import java.awt.event.KeyEvent;

public class HexBoard extends Board {

    /**
     *
     */
    private static final long serialVersionUID = 3485974281322138083L;
    protected int keyDirection;

    public HexBoard(int xCells, int yCells, int fieldSize) {
        super(xCells, yCells, fieldSize);
        keyDirection = 0;
    }

    @Override
    protected void generateBoard() {
        Point startingPoint = new Point(20, 20);
        int sD = (int)Math.round(Math.sqrt(3) * fieldSize);
        int margin = 2;

        for(int y = 0; y < yCells; y++) {
            for(int x = 0; x <xCells; x++) {
                fields[x][y] = new HexField(fieldSize, (Point) startingPoint.clone());
                startingPoint.translate(sD + margin, 0);
            }

            int dy = (2 * fieldSize) - (fieldSize / 2) + margin;
            int dx = -xCells * (sD + margin);
            dx += ((sD + margin) / 2);
            startingPoint.translate(dx, dy);
            
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        if(e.getKeyCode() == KeyEvent.VK_UP) {
            keyDirection = KeyEvent.VK_UP;           
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            keyDirection = KeyEvent.VK_DOWN;    
        }
        else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            if(keyDirection == 0) {
                new HumanMoveController(KeyEvent.VK_LEFT);  
            }
            else {
                new HumanMoveController(keyDirection, KeyEvent.VK_LEFT); 
            }
            keyDirection = 0;
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if(keyDirection == 0) {
                new HumanMoveController(KeyEvent.VK_RIGHT);  
            }
            else {
                new HumanMoveController(keyDirection, KeyEvent.VK_RIGHT);  
            }   
            keyDirection = 0;      
        }
        else {
            super.keyPressed(e);
        }
        
    }
    
}