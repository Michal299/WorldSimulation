package Models.Organisms.Plants;

import javax.swing.plaf.ColorUIResource;

import Models.Utilities.Point;
import Models.Worlds.TakenPlaceException;
import Models.Worlds.World;

public class Grass extends Plant {

    public Grass(World world, Point position, int birthRound) {
        super(world, position, birthRound, 1, 1, 'T', new ColorUIResource(4, 255, 0), 25);
    }

    @Override
    protected void spread() {
        
        var result = preSpread();
        if(result.getKey()) {
            var child = new Grass(world, result.getValue(), world.getRoundNumber());
            world.getCommentator().reportSpread(this, result.getValue());
            try {
                world.addChild(child);
            }
            catch (TakenPlaceException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public String toString() {
        return "Trawa" + position.toString();
    } 
}