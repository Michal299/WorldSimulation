package Models.Organisms.Plants;

import javax.swing.plaf.ColorUIResource;

import Models.Organisms.Organism;
import Models.Organisms.Animals.Animal;
import Models.Organisms.Animals.CyberSheep;
import Models.Utilities.Point;
import Models.Worlds.TakenPlaceException;
import Models.Worlds.World;

public class SosnowskyHogweed extends Plant {

    public SosnowskyHogweed(World world, Point position, int birthRound) {
        super(world, position, birthRound, 10, 1, 'B', new ColorUIResource(173, 66, 89), 25);
    }

    @Override
    public void action() {
        killAnimalsAround();
        super.action();
    }

    @Override
    public void collision(Organism opponent) {
        if(opponent instanceof CyberSheep) {
            super.collision(opponent);
        }
        else {
            world.getCommentator().reportDeath(opponent, this);
            kill(opponent);
        }    
    }

    @Override
    protected void spread() {
        var result = preSpread();
        if(result.getKey()) {

            var child = new SosnowskyHogweed(world, result.getValue(), world.getRoundNumber());
            world.getCommentator().reportSpread(this, result.getValue());
            try {
                world.addChild(child);
            }
            catch (TakenPlaceException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void killAnimalsAround() {
        var placesAround = world.getPlacesAround(position, 1);
        for(var place : placesAround) {
            var organism = world.getOrganismFromSurface(place); 
            if(organism != null && organism instanceof Animal && !(organism instanceof CyberSheep)) {
                
                world.getCommentator().reportDeath(organism, this);
                kill(organism);
            }
        }
    }

    private void kill(Organism organism) {
        organism.decrementLives(organism.getLives());
        if(organism.isAlive() == false) {
            world.removeFromSurface(organism);
        }
    }

    @Override
    public String toString() {
        return "Barszcz sosnowskiego" + position.toString();
    }
}