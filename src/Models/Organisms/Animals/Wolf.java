package Models.Organisms.Animals;

import java.awt.Color;

import Models.Organisms.Organism;
import Models.Utilities.Point;
import Models.Worlds.TakenPlaceException;
import Models.Worlds.World;

public class Wolf extends Animal {

    public Wolf(World world, Point position, int birthRound) {
        super(world, position, birthRound, 9, 5, 1, 'W', Color.DARK_GRAY, 70,
                2);
    }

    @Override
    protected void reproduction(Organism reproducer) {
        var result = preReproduction(reproducer);
        
        if(result.getKey() == true)
        {
            var childPosition = result.getValue();
            var child = new Wolf(world, childPosition, world.getRoundNumber());
            world.getCommentator().reportBirthday(this, reproducer, child);
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
        return "Wilk" + position.toString();
    }
    
}