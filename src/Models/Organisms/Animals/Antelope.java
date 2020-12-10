package Models.Organisms.Animals;

import java.util.Random;

import javax.swing.plaf.ColorUIResource;

import Models.Organisms.Organism;
import Models.Utilities.Point;
import Models.Worlds.TakenPlaceException;
import Models.Worlds.World;

public class Antelope extends Animal {

    public Antelope(World world, Point position, int birthRound) {
        super(world, position, birthRound, 4, 4, 1, 'A', new ColorUIResource(217, 160, 91), 90, 4);

    }

    @Override
    public void collision(Organism opponent) {
        var gen = new Random();
        
        if(opponent.getClass().equals(this.getClass())) {
            reproduction(opponent);
        }
        else {
            if(gen.nextInt(2) == 0) {
                var escapePoints = world.getFreePlacesAround(position);
                if(escapePoints.size() == 0) {
                    super.collision(opponent);
                }
                else {
                    world.getCommentator().reportEscape(this, opponent);

                    var nextPosition = escapePoints.get(gen.nextInt(escapePoints.size()));
                    var prevPosition = (Point) position.clone();

                    world.getCommentator().reportMove(this, nextPosition);
                    
                    this.setPosition(nextPosition);
                    try {
                        world.updateOrganism(this, prevPosition);
                    }
                    catch (TakenPlaceException e) {
                        System.out.println(e.getMessage());
                    }

                    world.getCommentator().reportMove(opponent, prevPosition);
                    var prevOpponent = (Point) opponent.getPosition().clone();
                    opponent.setPosition(prevPosition);
                    
                    try {
                        world.updateOrganism(opponent, prevOpponent);
                    }
                    catch (TakenPlaceException e) {
                        System.out.println(e.getMessage());
                    }


                }
            }
            else {
                super.collision(opponent);
            }
        }
    }

    @Override
    public Point movePoint() {
        var gen = new Random();

        var stepLength = gen.nextInt(2) + 1;

        var points = world.getPlacesAround(position, stepLength);
        if(points.size() > 0) {
            var index = gen.nextInt(points.size());
            return points.get(index);
        }
        else {
            return position;
        }
    }
    
    @Override
    public String toString() {
        return "Anylopa" + position.toString();
    }

    @Override
    protected void reproduction(Organism reproducer) {
        var preReproduction = preReproduction(reproducer);
        if(preReproduction.getKey() == true) {
            
            var child = new Antelope(world, preReproduction.getValue(), world.getRoundNumber());
            
            world.getCommentator().reportBirthday(this, reproducer, child);
            
            try {
                world.addChild(child);
            }
            catch (TakenPlaceException e) {
                System.out.println(e.getMessage());
            }
        }

    }
    
}