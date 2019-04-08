package simulation.physics;

import simulation.physics.engine.FieldController;
import simulation.physics.particle.Particle;

public class Test {

    public static void main(String[] args) {
        FieldController field = new FieldController(Particle.ELECTRON_MASS/2);
        field.addParticle(new Particle(-Particle.ELECTRON_CHARGE , 100, 90));
        new Thread(field).start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        field.setRunning(false);
    }
}
