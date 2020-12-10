package Models.Organisms.Animals;

import java.util.Random;

import javax.swing.plaf.ColorUIResource;

import Models.Organisms.Organism;
import Models.Utilities.Point;
import Models.Worlds.TakenPlaceException;
import Models.Worlds.World;

public class Fox extends Animal {

    public Fox(World world, Point position, int birthRound) {
        super(world, position, birthRound, 3, 7, 1, 'L', new ColorUIResource(247, 136, 0), 70, 3);
    }

    @Override
    public Point movePoint() {
        var movePoints = world.getPlacesAround(position, 1);
        for(int i = 0; i < movePoints.size(); i++) {
            
            var point = movePoints.get(i);
            var organism = world.getOrganismFromSurface(point);
            if(organism != null && organism.getStrength() > this.getStrength()) {

                movePoints.remove(i);
                i--;
            }
        }

        if(movePoints.size() > 0) {
            var generator = new Random();
            var index = generator.nextInt(movePoints.size());
            return movePoints.get(index);
        }
        else {
            return position;
        }
    }

    @Override
    protected void reproduction(Organism reproducer) {
        
        var preReproduction = preReproduction(reproducer);
        if(preReproduction.getKey() == true) {
            
            var child = new Fox(world, preReproduction.getValue(), world.getRoundNumber());
            
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
        return "Lis" + position.toString();
    }
    
}