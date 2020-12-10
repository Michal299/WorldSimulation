package Models.Organisms.Animals;

import javax.swing.plaf.ColorUIResource;

import Models.Organisms.Organism;
import Models.Utilities.Point;
import Models.Worlds.TakenPlaceException;
import Models.Worlds.World;

public class Sheep extends Animal {

    public Sheep(World world, Point position, int birthRound) {
        super(world, position, birthRound, 4, 4, 1, 'O', new ColorUIResource(232, 230, 220), 75,
                2);
    }

    @Override
    protected void reproduction(Organism reproducer) {
        
        var result = preReproduction(reproducer);
        
        if(result.getKey() == true)
        {
            var childPosition = result.getValue();
            var child = new Sheep(world, childPosition, world.getRoundNumber());
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
        return "Owca" + position.toString();
    }
    
}