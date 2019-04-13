package simulation.gui;

import javafx.scene.shape.Circle;
import simulation.physics.appendix.Vector2D;
import simulation.physics.particle.MoveableParticle;

import static simulation.physics.particle.Particle.ELECTRON_MASS;

public class Charge {
    private Circle circle;
    private MoveableParticle mp;
    public void setCircle(double x, double y){
        circle = new Circle();
        circle.setLayoutX(x);
        circle.setLayoutY(y);
        circle.setRadius(5.0f);
        mp = new MoveableParticle(
                x,y,
                new Vector2D(x,y),
                ELECTRON_MASS
        );
    }
    public void changePos(double x, double y){
        circle.setLayoutX(x);
        circle.setLayoutY(y);
    }
    public Circle getCircle(){
        System.out.println(circle.getCenterY());
        return circle;
    }

    public void  foo() {
        System.out.println(circle.getLayoutY());
    }
}
