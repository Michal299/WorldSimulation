package Models.Organisms.Plants;

import javax.swing.plaf.ColorUIResource;

import Models.Organisms.Organism;
import Models.Utilities.Point;
import Models.Worlds.TakenPlaceException;
import Models.Worlds.World;

public class Belladonna extends Plant {

    public Belladonna(World world, Point position, int birthRound) {
        super(world, position, birthRound, 99, 1, 'J', new ColorUIResource(119, 0, 255), 25);
        
    }

    @Override
    public String toString() {
        return "Wilcze jagody" + position.toString();
    }

    @Override
    protected void fight(Organism opponent) {
        kill(opponent);
        lives--;
    }

    @Override
    protected void spread() {
        var result = preSpread();
        if(result.getKey()) {

            var child = new Belladonna(world, result.getValue(), world.getRoundNumber());
            world.getCommentator().reportSpread(this, result.getValue());
            try {
                world.addChild(child);
            }
            catch (TakenPlaceException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    private void kill(Organism organism) {
        organism.decrementLives(organism.getLives());
    }

    
}