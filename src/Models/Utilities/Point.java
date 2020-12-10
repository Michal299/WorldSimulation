package Models.Utilities;

public class Point implements Cloneable {

    protected int x;
    protected int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @param expression in format "(x, y)", for example: "(1, 2)"
     */
    public Point(String expression) {
        expression = expression.replace('(', ' ');
        expression = expression.replace(',', ' ');
        expression = expression.replace(')', ' ');
        var values = expression.split(" ");
        
        try {
            this.x = Integer.parseInt(values[1]);
            this.y = Integer.parseInt(values[2]);
        }
        catch (NumberFormatException e) {
            System.out.println(e.getLocalizedMessage());
        }
        
    }

    public Point(Point point) {
        this.x = point.x;
        this.y = point.y;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public void setX(int value) {
        x = value;
    }
    public void setY(int value) {
        y = value;
    }

    public void translate(int dx, int dy) {
        x += dx;
        y += dy;
    }

    public double distance(Point from) {
        
        int dx = from.getX() - this.getX();
        int dy = from.getY() - this.getY();
        
        dx = dx * dx;
        dy = dy * dy;

        var result = Math.sqrt(dx + dy);
        
        return result;
    }

    @Override
    public Object clone() {
        return new Point(this.x, this.y);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Point) {
            var point = (Point) obj;
            return (point.x == this.x && point.y == this.y);
        }
        else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}