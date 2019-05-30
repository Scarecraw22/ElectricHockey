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

    public static double angleBetweenVctrs(Vector2D v1, Vector2D v2){
        boolean orientation = (v1.getX()*v2.getY() - v1.getY()*v2.getX()) < 0;
        double smallAngle = Math.acos(scalarProduct(v1,v2)/(v1.length()*v2.length()));
        return orientation ? smallAngle : Math.PI*2 - smallAngle;
    }

    public static double scalarProduct(Vector2D v1, Vector2D v2){
        return v1.getX()*v2.getX() + v1.getY()*v2.getY();
    }

    public static Vector2D getPerpendicular(Vector2D v){
        if(v.x == 0 && v.y== 0) return new Vector2D(0,0);
        if(v.x == 0) return new Vector2D(1,0);
        if(v.y == 0) return new Vector2D(0,1);
        double y = Math.sqrt(1/(((v.getY()*v.getY()  )/(v.getX()*v.getX()))+1));
        return new Vector2D(-v.getY()*y/v.getX(), y);
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
        if (val == 0) val = 1e-7;
        return mul(1/val);
    }

    public String toString(){
        return "[" + x + "," + y + "]";
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
