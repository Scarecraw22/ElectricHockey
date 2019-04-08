package simulation.physics.particle;

import simulation.physics.appendix.Vector2D;

public class PositionSystem {

    private double x;
    private double y;

    public PositionSystem(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D positionToVector(){
        return new Vector2D(x, y);
    }

    public synchronized void moveX(double length){
        x += length;
    }

    public synchronized void moveY(double length){
        y += length;
    }

    public synchronized void move(Vector2D value){
        x += value.getX();
        y += value.getY();
    }

    public synchronized double getX() {
        return x;
    }

    public synchronized void setX(double x) {
        this.x = x;
    }

    public synchronized double getY() {
        return y;
    }

    public synchronized void setY(double y) {
        this.y = y;
    }
}
