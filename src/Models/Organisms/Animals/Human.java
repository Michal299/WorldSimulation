package Models.Organisms.Animals;


import javax.swing.plaf.ColorUIResource;

import Models.Abilities.Ability;
import Models.Abilities.Purification;
import Models.Organisms.Organism;
import Models.Utilities.Point;
import Models.Worlds.World;

public class Human extends Animal {

    private Point nextMovePoint;
    private Ability ability;

    public Human(World world, Point position, int birthRound) {
        super(world, position, birthRound, 5, 4, 1, 'H', new ColorUIResource(235, 171, 136), 0,
                999);
                
        nextMovePoint = null;
        ability = new Purification(5);
    }

    public void setNextMovePoint(Point point) {
        
        nextMovePoint = point;
    }

    public void setAbility(Ability ab) {
        ability = ab;
    }

    public Ability getAbility() {
        return ability;
    }
    
    @Override
    public void action() {
       
        if(ability != null) {
            ability.make(this);
        }
        
        super.action();

        if(ability != null) {
            ability.make(this);
        }
        
    }

    @Override
    public Point movePoint() {
        
        if(nextMovePoint != null) {
         
            Point result = nextMovePoint;
            nextMovePoint = null;
            return result;
        }
        else {
            
            return position;
        }
    }

    /**
     * Human cannot reproduce.
     */
    @Override
    protected void reproduction(Organism reproducer) {

    }

    @Override
    public String toString() {
        return "Człowiek" + position.toString();
    }

    
}