package Controllers;

import Models.Commentators.PolishCommentator;
import Models.Organisms.Animals.Human;
import Models.Utilities.Point;
import Models.Worlds.HexWorld;
import Models.Worlds.TakenPlaceException;
import Views.Boards.HexBoard;

public class HexInit {
    
    private int width;
    private int height;

    private boolean error;

    public HexInit(String width, String height) {
        
        error = false;
        
        try {
            this.width = Integer.parseInt(width);
            this.height = Integer.parseInt(height);
            
            if(this.width <= 0 || this.height <= 0) {
                error = true;
                return;
            }
        }
        catch (NumberFormatException e) {
            error = true;
            return;
        }

        var world = new HexWorld(this.width, this.height);
        var board = new HexBoard(this.width, this.height, 12);
        var frame = MainController.getMainFrame();

        world.addCommentator(new PolishCommentator(frame.getCommentsArea()));
        
        try {
            var position = new Point(world.getWidth()/2, world.getHeight()/2);
            world.addHero(new Human(world, position, world.getRoundNumber()));
        }
        catch (TakenPlaceException e) {
            
        }

        MainController.addWorld(world);
        frame.setBoard(board);
        board.updateBoard(world.toColors());
        
        frame.getMenuPanel().activateButtons();
        frame.revalidate();
    }

    public boolean isError() {
        return error;
    }
}