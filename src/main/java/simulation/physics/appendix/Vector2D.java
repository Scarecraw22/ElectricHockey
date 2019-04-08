package simulation.physics.appendix;

public class Vector2D {

    private double x;
    private double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static Vector2D constructFromCoordinates(double x1, double y1, double x2, double y2){
        return new Vector2D(x2 -x1, y2 - y1);
    }

    public static Vector2D constructFromCoordinates(Vector2D v1, Vector2D v2){
        return v1.sub(v2);
    }

    public double length(){
        return Math.sqrt(x*x + y*y);
    }

    public Vector2D add(Vector2D other){
        return new Vector2D(x + other.x, y + other.y);
    }

    public Vector2D sub(Vector2D other){
        return new Vector2D(x - other.x, y - other.y);
    }

    public Vector2D mul(double val){
        return new Vector2D(x*val, y*val);
    }

    public Vector2D div(double val){
        if (val == 0) throw new IllegalArgumentException("cannot divide by zero");
        return mul(1/val);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
