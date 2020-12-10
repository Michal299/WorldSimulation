package Views.Fields;

import Models.Utilities.Point;

public class SquaredField extends Field {

    public SquaredField(int edgeSize, Point position) {
        super(edgeSize, position);
    }

    @Override
    protected void generateBody(Point position) {
        
        body.addPoint(position.getX(), position.getY());
        body.addPoint(position.getX() + edgeSize, position.getY());
        body.addPoint(position.getX() + edgeSize, position.getY() + edgeSize);
        body.addPoint(position.getX(), position.getY() + edgeSize);
    
    }
    
}