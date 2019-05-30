package simulation.physics.particle;


import simulation.physics.appendix.Vector2D;

import javax.sound.midi.Soundbank;

public class Particle {

    public static final double k = 9000000000.;
    public static final double ELECTRON_MASS = 9.1093*1e-31;
    public static final double ELECTRON_CHARGE = 1.6021*1e-19;
    public static final double MI_ZERO_OVER_4PI = 1e4; //troche oszukane

    private double charge;
    private boolean isLinearlyMoving = false;
    private Vector2D velocity = new Vector2D(0,0);
    private PositionSystem position;

    public PositionSystem getPosition() {
        return position;
    }

    public Particle(double charge, double x, double y) {
        this.charge = charge;
        this.position = new PositionSystem(x, y);
    }

    public Vector2D calculateCoulombForceFrom(Particle other){
        Vector2D r = Vector2D.constructFromCoordinates(other.position.positionToVector(), position.positionToVector());
        return r.mul(k*charge*other.charge).div(Math.pow(r.length(), 3));
    }

    public Vector2D getElectricFieldAt(double x, double y){
        return calculateCoulombForceFrom(new Particle(1, x, y));
    }

    public double getMagneticFieldAt(double x, double y){
        if(!isLinearlyMoving) return 0;
        Vector2D r = Vector2D.constructFromCoordinates(new Vector2D(x, y), position.positionToVector());
        double angle = Vector2D.angleBetweenVctrs(velocity, r);
        return MI_ZERO_OVER_4PI*charge*velocity.length()*Math.sin(angle)/Math.pow(r.length(), 2);
    }

    public void setMoving(double xVel, double yVel){
        isLinearlyMoving = true;
        this.velocity = new Vector2D(xVel, yVel);
    }

    public void update(double delta){
        position.moveX((velocity.getX()*delta));
        position.moveY((velocity.getY()*delta));
    }

}
