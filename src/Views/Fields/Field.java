package Views.Fields;

import java.awt.Color;
import java.awt.Polygon;

import javax.swing.plaf.ColorUIResource;

import Models.Utilities.Point;

public abstract class Field {
    
    protected Color color;
    protected Polygon body;
    protected int edgeSize;

    public Field(int edgeSize, Point position) {
        this.edgeSize = edgeSize;
        color = new ColorUIResource(173, 181, 189);
        body = new Polygon();
        
        generateBody(position);
    }

    public Color getColor() {
        return color;
    }

    public Polygon getBody() {
        return body;
    }

    public int getEdgeSize() {
        return edgeSize;
    }

    public void setColor(Color value) {
        color = value;
    }

    protected abstract void generateBody(Point position);
}