package Models.Worlds;

import java.util.Vector;

import Models.Utilities.Point;

public class SquaredWorld extends World {

    public SquaredWorld(int width, int height) {
        super(width, height);
    }

    @Override
    public Vector<Point> getPlacesAround(Point position, int range) {
        
        var places = new Vector<Point>(4);

        if(position.getX() - range >= 0) {
            places.add(new Point(position.getX() - range, position.getY()));
        }
        
        if(position.getX() + range < width ) {
            places.add(new Point(position.getX() + range, position.getY()));
        }
        
        if(position.getY() - range >= 0) {
            places.add(new Point(position.getX(), position.getY() - range));
        }

        if(position.getY() + range < height) {
            places.add(new Point(position.getX(), position.getY() + range));
        }

        return places;
    }
    
}