package simulation.physics.particle;


import simulation.physics.appendix.Vector2D;

public class Particle {

    public static final double k = 9000000000.;
    public static final double ELECTRON_MASS = 9.1093*1e-31;
    public static final double ELECTRON_CHARGE = 1.6021*1e-19;

    private double charge;

    public PositionSystem getPosition() {
        return position;
    }

    private PositionSystem position;

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

}
