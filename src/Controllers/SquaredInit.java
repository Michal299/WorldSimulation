package Controllers;

import Models.Commentators.PolishCommentator;
import Models.Organisms.Animals.Human;
import Models.Utilities.Point;
import Models.Worlds.SquaredWorld;
import Models.Worlds.TakenPlaceException;
import Views.Boards.SquaredBoard;

public class SquaredInit {
    
    private int width;
    private int height;

    private boolean error;

    public SquaredInit(String width, String height) {
        
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

        var world = new SquaredWorld(this.width, this.height);
        var board = new SquaredBoard(this.width, this.height, 20);
        var frame = MainController.getMainFrame();
        
        try {
            var position = new Point(world.getWidth()/2, world.getHeight()/2);
            world.addHero(new Human(world, position, world.getRoundNumber()));
        }
        catch (TakenPlaceException e) {
            
        }

        world.addCommentator(new PolishCommentator(frame.getCommentsArea()));
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