package simulation.gui;

import javafx.application.Platform;
import javafx.scene.shape.Circle;
import simulation.physics.engine.FieldController;
import simulation.physics.particle.Particle;

import java.util.Map;


public class Updater implements Runnable {

    private FieldController field;
    private boolean isRunning;
    private Charge c;
    private Controller controller;
    private Map<Circle, Particle> particles;

    private static final int FPS = 30;


    public Updater(Controller controller) {
        this.controller = controller;
    }
    public void setC(Charge c) {
        this.c = c;
    }

    public void setField(FieldController field) {
        this.field = field;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public void setParticles(Map<Circle, Particle> particles){ this.particles = particles; }
    @Override
    public void run() {

        long lastLoopTime = System.nanoTime();
        isRunning = true;
        double passed = 0;
        while (isRunning){
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;
            controller.checkCollision();
            double delta = updateLength/1e9;
            passed += delta;
            if (passed > 1./FPS){
                c.changePos(field.getBallX(),field.getBallY());
                for (Map.Entry<Circle,Particle> entry : particles.entrySet()){
                    entry.getKey().setTranslateX(entry.getValue().getPosition().getX());
                    entry.getKey().setTranslateY(entry.getValue().getPosition().getY());
                }
                Platform.runLater(() -> controller.updateElectricField());
                Platform.runLater(() -> controller.updateMagneticField());
                passed = 0;
            }

        }
    }
}
