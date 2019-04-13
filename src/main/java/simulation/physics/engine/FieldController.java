package simulation.physics.engine;

import simulation.physics.appendix.Vector2D;
import simulation.physics.particle.MoveableParticle;
import simulation.physics.particle.Particle;

import java.util.ArrayList;

public class FieldController implements Runnable{


    private ArrayList<Particle> particles;
    private MoveableParticle ball;
    private boolean isRunning;

    public FieldController(double ballMass){
        particles = new ArrayList<>();
        ball = new MoveableParticle(100,100, new Vector2D(0,0), ballMass);
    }

    public synchronized double getBallX(){
        return ball.getPosition().getX();
    }

    public synchronized double getBallY(){
        return ball.getPosition().getY();
    }

    public synchronized void addParticle(Particle p) { particles.add(p); }

    public synchronized boolean removeParticle(Particle p) { return particles.remove(p); }

    public synchronized Vector2D getFieldAt(double x, double y){
        return particles.stream()
                .map(particle -> particle.getElectricFieldAt(x, y))
                .reduce(new Vector2D(0,0), Vector2D::add);
    }

    private void update(double delta){
        Vector2D force = getFieldAt(getBallX(), getBallY());
        ball.update(delta, force);
    }

    public void run(){
        long lastLoopTime = System.nanoTime();
        isRunning = true;
        double passed = 0;
        while (isRunning){
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;
            double delta = updateLength/1e9;
            passed += delta;
            //TODO remove logging if not needed
            if (passed > 0.2){
                //System.out.println(getBallX() + " |||||| " +getBallY());
                passed = 0;
            }
            update(delta);
        }
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public ArrayList<Particle> getParticles() {
        return particles;
    }


}
