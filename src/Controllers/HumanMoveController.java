package Controllers;

import java.awt.event.KeyEvent;

import Models.Utilities.Point;

public class HumanMoveController {
    
    private int dx;
    private int dy;

    public HumanMoveController(int firstClick, int secondClick) {
        
        dx = 0;
        dy = 0;
        dispatch(firstClick);
        dispatch(secondClick);
        
        setHumanMove();
    }

    public HumanMoveController(int click) {
        
        dx = 0;
        dy = 0;
        dispatch(click);

        setHumanMove();
    }

    private void dispatch(int clickKey) {
        switch(clickKey) {

            case KeyEvent.VK_LEFT:
                if(dy != -1) {
                    dx--;
                }

            break;
        
            case KeyEvent.VK_DOWN:
                dy++;
            break;
        
            case KeyEvent.VK_RIGHT:
                if(dy != 1) {
                    dx++;
                }
            break;
        
            case KeyEvent.VK_UP:
                dy--;
            break;
        
        }
    }

    private void setHumanMove() {
        
        var world = MainController.getWorld();
        var hero = world.getHero();
        if(hero == null) {
            return;
        }

        var position = hero.getPosition();
        var surrounding = world.getPlacesAround(position, 1);

        var potentialPosition = ((Point)position.clone());
        potentialPosition.translate(dx, dy);

        for(var point : surrounding) {
            if(point.equals(potentialPosition)) {
                
                hero.setNextMovePoint(potentialPosition);
                // world.getCommentator().reportMoveAttempt(hero, potentialPosition);
                new NextRoundController();
            }
        }
    }
}