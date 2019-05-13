package simulation.physics.particle;

import simulation.physics.appendix.Vector2D;

public class MoveableParticle {

    private PositionSystem position;
    private Vector2D velocity;
    private double mass;

    public MoveableParticle(PositionSystem position, Vector2D velocity, double mass) {
        this.position = position;
        this.velocity = velocity;
        this.mass = mass;
    }

    public MoveableParticle(double x, double y, Vector2D velocity, double mass) {
        this.position = new PositionSystem(x, y);
        this.velocity = velocity;
        this.mass = mass;
    }
    public void setPosition(PositionSystem pos){
        position=pos;
    }
    public void update(double delta, Vector2D electricField){
        Vector2D acceleration = truncateField(electricField).mul(Particle.ELECTRON_CHARGE).div(mass);
        //TODO remove logging if not needed
        //System.out.println(position.getX() + ", " + position.getY() + " v0: " + velocity.length() + "   acc: " + acceleration.length() + " field: " + electricField.length());
        position.moveX(velocity.getX()*delta + acceleration.getX()*delta*delta/2);
        position.moveY(velocity.getY()*delta + acceleration.getY()*delta*delta/2);
        velocity = velocity.add(acceleration.mul(delta));
    }

    private static Vector2D truncateField(Vector2D field){
        if (field.length() > 1e-8){
            return new Vector2D(field.getX()/(field.length()/1e-8), field.getY()/(field.length()/1e-8));
        }
        return field;
    }

    public PositionSystem getPosition() {
        return position;
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public double getMass() {
        return mass;
    }



}
