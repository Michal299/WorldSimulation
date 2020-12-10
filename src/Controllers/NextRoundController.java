package Controllers;

public class NextRoundController {
    
    public NextRoundController() {
        var world = MainController.getWorld();
        world.makeRound();
        
        var frame = MainController.getMainFrame();
        var board = frame.getBoard();
        board.updateBoard(world.toColors());

        frame.repaint();

    }
}