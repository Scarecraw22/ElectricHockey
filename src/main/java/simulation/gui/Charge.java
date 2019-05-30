package simulation.gui;

import javafx.scene.shape.Circle;

public class Charge {
    private Circle circle;

    public void setCircle(double x, double y, Circle circle){
        this.circle = circle;
        circle.setLayoutX(x);
        circle.setLayoutY(y);
        circle.setRadius(6.5f);

    }
    public void changePos(double x, double y){
        circle.setLayoutX(x);
        circle.setLayoutY(y);
    }

    public Circle getCircle(){
        return circle;
    }

}
