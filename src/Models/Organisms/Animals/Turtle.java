package Models.Organisms.Animals;

import java.util.Random;

import javax.swing.plaf.ColorUIResource;

import Models.Organisms.Organism;
import Models.Utilities.Point;
import Models.Worlds.TakenPlaceException;
import Models.Worlds.World;

public class Turtle extends Animal {

    public Turtle(World world, Point position, int birthRound) {
        super(world, position, birthRound, 2, 1, 1, 'Z', new ColorUIResource(23, 107, 20),  100, 1);
    }

    private boolean attackBlocked(Organism attacker) {
        
        if(attacker.getStrength() < 5) {
            return true;
        }
        else {
            return false;
        }

    }

    @Override
    public void action() {
        
        var gen = new Random();
        if(gen.nextInt(100) + 1 < 75) {
            world.getCommentator().reportStayInPlace(this);
        }
        else {
            super.action();
        }
        
    }

    @Override
    public void collision(Organism opponent) {
        
        if(opponent.getClass().equals(this.getClass())) {
            super.collision(opponent);
        }
        else {  
            if(attackBlocked(opponent)) {
                world.getCommentator().reportBlock(this, opponent);
            }
            else {
                super.collision(opponent);
            }
        }
        
    }

    @Override
    protected void reproduction(Organism reproducer) {
        var result = preReproduction(reproducer);
        
        if(result.getKey() == true)
        {
            var childPosition = result.getValue();
            var child = new Turtle(world, childPosition, world.getRoundNumber());
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
        return "Żółw" + position.toString();
    }
}