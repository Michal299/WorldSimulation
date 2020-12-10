package Controllers;

public class PrevBoardController {
    
    public PrevBoardController() {
        var frame = MainController.getMainFrame();
        var board = frame.getBoard();

        board.switchCurrentsBoards();

        frame.repaint();
    }
}