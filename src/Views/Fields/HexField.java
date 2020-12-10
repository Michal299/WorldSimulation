package Views.Fields;

import Models.Utilities.Point;

public class HexField extends Field {

    public HexField(int edgeSize, Point position) {
        super(edgeSize, position);
    }

    @Override
    protected void generateBody(Point position) {
        
        int a, b;
        a = position.getX();
        b = position.getY();

        double sD = (double)edgeSize * Math.sqrt(3);
        double lD = 2 * edgeSize;

        int x, y;
        //First vertex
        x = a;
        y = b;
        body.addPoint(x, y);

        //Second vertex
        x = (int) Math.round(a + (sD/2));
        y = (int) Math.round(b + edgeSize/2);
        body.addPoint(x, y);

        //Third vertex
        x = (int) Math.round(a + (sD/2));
        y = (int) Math.round(b + edgeSize/2) + edgeSize;
        body.addPoint(x, y);

        //Fourth vertex
        x = a;
        y = (int) Math.round(b + lD);
        body.addPoint(x, y);

        //Fifth vertex
        x = (int) Math.round(a - sD/2);
        y = (int) Math.round(b + edgeSize/2) + edgeSize;
        body.addPoint(x, y);

        //Sixth vertex
        x = (int) Math.round(a - (sD/2));
        y = (int) Math.round(b + edgeSize/2);
        body.addPoint(x, y);

    }
    
}