package Models.Worlds;

import java.util.Vector;

import Models.Utilities.Point;

public class HexWorld extends World {

    public HexWorld(int width, int height) {
        super(width, height);
        
    }

    @Override
    public Vector<Point> getPlacesAround(Point position, int range) {
        var result = new Vector<Point>(6);
        
        int x, y;
        x = position.getX();
        y = position.getY();
        
        if(x - range >= 0) {
            result.add(new Point(x - range, y));

            if(y + range < height) {
                result.add(new Point(x - range, y + range));
            }
        }

        if(x + range < width) {
            result.add(new Point(x + range, y));
            
            if(y - range >= 0) {
                result.add(new Point(x + range, y - range));
            }
        }

        if(y - range >= 0) {
            result.add(new Point(x, y - range));
        }

        if(y + range < height) {
            result.add(new Point(x, y + range));
        }

        
        return result;
    }
    
}