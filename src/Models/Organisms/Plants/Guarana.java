package Models.Organisms.Plants;

import javax.swing.plaf.ColorUIResource;

import Models.Organisms.Organism;
import Models.Utilities.Point;
import Models.Worlds.TakenPlaceException;
import Models.Worlds.World;

public class Guarana extends Plant {

    private int boostValue;

    public Guarana(World world, Point position, int birthRound) {
        super(world, position, birthRound, 1, 1, 'G', new ColorUIResource(255, 36, 36), 30);
        boostValue = 4;
    }

    @Override
    public void collision(Organism opponent) {
        
        fight(opponent);
        if (this.isAlive() == false) {
            world.removeFromSurface(this);
            
            world.getCommentator().reportDeath(this, opponent);
            
            world.getCommentator().reportBoost(opponent, this, boostValue);    
            boostOpponent(opponent);
            
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
    protected void spread() {
        var result = preSpread();
        if(result.getKey()) {
            
            var child = new Guarana(world, result.getValue(), world.getRoundNumber());
            world.getCommentator().reportSpread(this, result.getValue());
            try {
                world.addChild(child);
            }
            catch (TakenPlaceException e) {
                System.out.println(e.getMessage());
            }
        }

    }
    
    private void boostOpponent(Organism opponent) {
        var strength = opponent.getStrength();
        strength += boostValue;
        opponent.setStrength(strength);
    }

    @Override
    public String toString() {
        return "Guarana" + position.toString();
    }
}