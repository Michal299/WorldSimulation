package Models.Organisms.Animals;

import java.util.Random;
import java.util.Vector;

import javax.swing.plaf.ColorUIResource;

import Models.Organisms.Organism;
import Models.Organisms.Plants.SosnowskyHogweed;
import Models.Utilities.Point;
import Models.Worlds.TakenPlaceException;
import Models.Worlds.World;

public class CyberSheep extends Animal {

    public CyberSheep(World world, Point position, int birthRound) {
        super(world, position, birthRound, 11, 4, 1, 'C', new ColorUIResource(135, 135, 112), 40,
                0);
    }

    @Override
    public Point movePoint() {
        
        var sosnowskysPosition = findSosnowskyHogweeds();
        
        if(sosnowskysPosition.size() == 0) {
            return super.movePoint();
        }
        else {
            var point = findClosestPoint(sosnowskysPosition);
            
            var freePoints = world.getFreePlacesAround(position);
            var any = bringCloser(freePoints, point);
            
            if(any.size() == 0) {
                var movePoints = world.getPlacesAround(position, 1);
                any = bringCloser(movePoints, point);

                var gen = new Random();
                var result = any.get(gen.nextInt(any.size()));
                return result;
            }
            else {
                var gen = new Random();
                var result = any.get(gen.nextInt(any.size()));
                return result;
            }
        }
    }

    @Override
    public String toString() {
        return "Cyber owca" + position.toString();
    }

    @Override
    protected void reproduction(Organism reproducer) {
        var preReproduction = preReproduction(reproducer);
        if(preReproduction.getKey() == true) {
            
            var child = new CyberSheep(world, preReproduction.getValue(), world.getRoundNumber());
            
            world.getCommentator().reportBirthday(this, reproducer, child);
            
            try {
                world.addChild(child);
            }
            catch (TakenPlaceException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Vector<Point> findSosnowskyHogweeds() {
        var points = new Vector<Point>();
    
        points = world.findAll(SosnowskyHogweed.class);

        return points;
    }

    private Point findClosestPoint(Vector<Point> points) {
        
        var closestPoint = points.get(0);
        
        for(var point : points) {
            if(point.distance(position) <= closestPoint.distance(position)) {
                closestPoint = point;
            }
        } 

        return closestPoint;
    }
    
    private Vector<Point> bringCloser(Vector<Point> points, Point destination) {
        
        var resultPoints = new Vector<Point>();
        
        var minimalDistance = position.distance(destination);
        
        for(var point : points) {
            if(point.distance(destination) < minimalDistance) {
                resultPoints.add(point);
            }
        }
        
        return resultPoints;
    }

}