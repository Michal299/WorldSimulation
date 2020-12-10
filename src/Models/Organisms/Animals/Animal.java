package Models.Organisms.Animals;

import java.awt.Color;
import java.util.Random;

import Models.Organisms.Organism;
import Models.Utilities.Pair;
import Models.Utilities.Point;
import Models.Worlds.TakenPlaceException;
import Models.Worlds.World;

public abstract class Animal extends Organism {

    protected int reproductionAbility;
    protected int ageOfReproductionAbility;


    public Animal(World world, Point position, int birthRound, int strength, int initiative, int lives, char symbol,
            Color color, int reproductionAbility, int ageOfReproductionAbility) {
        super(world, position, birthRound, strength, initiative, lives, symbol, color);
        this.reproductionAbility = reproductionAbility;
        this.ageOfReproductionAbility = ageOfReproductionAbility;
    }
    
    public void fight(Organism opponent) {
        if (opponent.getStrength() > strength) {
            lives--;
        }
        else if (opponent.getStrength() == strength) {
            lives--;
        }
        else if (opponent.getStrength() < strength) {
            opponent.decrementLives(1);
        }
    }

    @Override
    public void action() {
        var nextPoint = movePoint();
        if(nextPoint.equals(position) == false) {
            
            world.getCommentator().reportMoveAttempt(this, nextPoint);

            var organism = world.getOrganismFromSurface(nextPoint);
            if(organism == null) {
                
                world.getCommentator().reportMove(this, nextPoint);

                var prev = position;
                position = nextPoint;

                try {
                    world.updateOrganism(this, prev);
                }
                catch (TakenPlaceException e) {
                    System.out.println(e.getMessage());
                }
            }
            else {
                organism.collision(this);
            }
        }
        else {
            world.getCommentator().reportStayInPlace(this);
        }
    }

    @Override
    public void collision(Organism opponent) {
        
        if(opponent.getClass().equals(this.getClass())) {

            reproduction(opponent);
        }
        else {
            world.getCommentator().reportFight(opponent, this);
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
            else if (opponent.isAlive() == false) {
                world.getCommentator().reportDeath(opponent, this);
                world.removeFromSurface(opponent);
            }
        } 
    }

    /**
     * Default method choosing destination of animal's move in range of 1 from actual position.
     * @return random point from avaliable point in world. If there is no such point returns actual position.
     */
    public Point movePoint() {
        var points = world.getPlacesAround(position, 1);
        if(points.size() > 0) {
            var generator = new Random();
            var index = generator.nextInt(points.size());
            return points.get(index);
        }
        else {
            return position;
        }
    }

    protected Pair<Boolean, Point> preReproduction(Organism reproducer) {
        
        world.getCommentator().reportReproductionAttempt(reproducer, this);
        if(reproducer.getAge(world.getRoundNumber()) < ageOfReproductionAbility || this.getAge(world.getRoundNumber()) < ageOfReproductionAbility) {
            return new Pair<>(false, null);
        }

        var generator = new Random();
        var coefficient = generator.nextInt(100) + 1;
        
        Point childPosition = null;

        if(coefficient <= reproductionAbility) {
            
            var reproducetPositon = reproducer.getPosition();
            var positions = world.getFreePlacesAround(reproducetPositon);
            if(positions.size() > 0) {

                childPosition = positions.get(generator.nextInt(positions.size()));
            
            }
            else {
                positions = world.getFreePlacesAround(this.getPosition());
                if(positions.size() > 0) {

                    childPosition = positions.get(generator.nextInt(positions.size()));
                
                }
            }
        }

        if(childPosition != null) {
            return new Pair<Boolean, Point>(true, childPosition);
        }
        else {
            return new Pair<Boolean, Point>(false, null);
        }
    }

    protected abstract void reproduction(Organism reproducer);
}