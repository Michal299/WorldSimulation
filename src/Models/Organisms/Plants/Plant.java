package Models.Organisms.Plants;

import java.awt.Color;
import java.util.Random;

import Models.Organisms.Organism;
import Models.Utilities.Pair;
import Models.Utilities.Point;
import Models.Worlds.TakenPlaceException;
import Models.Worlds.World;

public abstract class Plant extends Organism {

    protected int spreadChance;

    public Plant(World world, Point position, int birthRound, int strength, int lives, char symbol,
            Color color, int spreadChance) {
        super(world, position, birthRound, strength, 0, lives, symbol, color);
        
        this.spreadChance = spreadChance;
    }

    @Override
    public void collision(Organism opponent) {

        fight(opponent);
        if (this.isAlive() == false) {
            world.removeFromSurface(this);
        
            world.getCommentator().reportDeath(this, opponent);
                
            world.getCommentator().reportMove(opponent, position);

            Point prevOpponentPosition = (Point) opponent.getPosition().clone();
            opponent.setPosition((Point)position.clone());
                
            try {
                world.updateOrganism(opponent, prevOpponentPosition);
            }
            catch (TakenPlaceException e) {
                System.out.println(e.getMessage());
            }
        }
        if (opponent.isAlive() == false) {
            world.getCommentator().reportDeath(opponent, this);
            world.removeFromSurface(opponent);
        }
    }

    @Override
    public void action() {
    
        var gen = new Random();
        var willSpread = gen.nextInt(100) + 1 <= spreadChance ? true : false;
        
        if(willSpread) {
            spread();
        }
        
    }
    
    protected void fight(Organism opponent) {
        
        if(opponent.getStrength() < this.getStrength()) {
            opponent.decrementLives(1);
        }

        this.decrementLives(1);
    }

    protected Pair<Boolean, Point> preSpread() {
        var freePoints = world.getFreePlacesAround(position);
        
        if(freePoints.size() == 0) {

            return new Pair<>(false, null);
        }
        else {

            var gen = new Random();
            var childPosition = freePoints.get(gen.nextInt(freePoints.size()));

            return new Pair<>(true, childPosition);
        }
    }

    /**
     * Using with preSpread is recommended!
     */
    protected abstract void spread();
}