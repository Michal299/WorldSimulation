package Models.Organisms.Plants;


import javax.swing.plaf.ColorUIResource;

import Models.Utilities.Point;
import Models.Worlds.TakenPlaceException;
import Models.Worlds.World;

public class SowThistle extends Plant {

    public SowThistle(World world, Point position, int birthRound) {
        super(world, position, birthRound, 1, 1, 'M', new ColorUIResource(252, 255, 36), 20);
    }

    @Override
    public void action() {
        for(var i = 0; i < 3; i++) {
            super.action();
        }
    }

    @Override
    public String toString() {
        return "Mlecz" + position.toString();
    }
    
    @Override
    protected void spread() {
        var result = preSpread();
        if(result.getKey()) {

            var child = new SowThistle(world, result.getValue(), world.getRoundNumber());
            world.getCommentator().reportSpread(this, result.getValue());
            try {
                world.addChild(child);
            }
            catch (TakenPlaceException e) {
                System.out.println(e);
            }
        }

    }

}