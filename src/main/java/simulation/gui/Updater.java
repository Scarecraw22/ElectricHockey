package simulation.gui;

import simulation.physics.engine.FieldController;


public class Updater implements Runnable {

    private FieldController field;
    private boolean isRunning;
    private Charge c;
    private Controller controller;


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
            if (passed > 0.016){
                c.changePos(field.getBallX(),field.getBallY());
                passed = 0;
            }

        }
    }
}
