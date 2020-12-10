package Models.Abilities;

import Models.Organisms.Organism;

public class Purification extends Ability {

    private int runCounter;

    public Purification(int delay) {
        super(delay);
        active = false;

        runCounter = 0;
    }

    public Purification(int delay, boolean state, int currentStateTime) {
        super(delay, state, currentStateTime);
    }

    @Override
    public void make(Organism owner) {
        
        runCounter++;
        if(runCounter == 2) {
            incrementCurrentTime();
            runCounter = 0;
        }

        if(!active) {
            return;
        } 
        
        var world = owner.getWorld();
        var surroundings = world.getPlacesAround(owner.getPosition(), 1);
        
        for(var place : surroundings) {

            var organism = world.getOrganismFromSurface(place);
            if(organism != null) {

                world.getCommentator().reportDeath(organism, owner);
                kill(organism);
                world.removeFromSurface(organism);
            }
        }

        
    }

    private void kill(Organism organism) {
        var lives = organism.getLives();
        organism.decrementLives(lives);
    }

    
    
}