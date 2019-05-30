package simulation.gui;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class VectorZ {

    private Group g = new Group();
    private Color color;
    private static final double BIG_RADIUS = 5.0;
    private static final double SMALL_RADIUS = 2.0;
    private static final double VALUE_CONSTANT = 1e20;

    public VectorZ(double x, double y, double value){
        Circle outside = new Circle(BIG_RADIUS, Color.TRANSPARENT);
        color = calculateColorFromMagnitude(Math.abs(value*VALUE_CONSTANT));
        outside.setTranslateX(x);
        outside.setTranslateY(y);
        outside.setStroke(color);
        outside.setStrokeWidth(1);
        g.getChildren().add(outside);
        if(value > 0) {
            Circle inside = new Circle(SMALL_RADIUS, color);
            inside.setTranslateX(x);
            inside.setTranslateY(y);
            g.getChildren().add(inside);
        }
        else if(value < 0){
            double offset = BIG_RADIUS*Math.sqrt(2)/2;
            Line l1 = new Line(outside.getTranslateX()-offset,outside.getTranslateY()-offset,outside.getTranslateX()+offset,outside.getTranslateY()+offset);
            Line l2 = new Line(outside.getTranslateX()-offset,outside.getTranslateY()+offset,outside.getTranslateX()+offset,outside.getTranslateY()-offset);
            l1.setStroke(color);
            l2.setStroke(color);
            g.getChildren().addAll(l1, l2);
        }

    }

    public Color calculateColorFromMagnitude(double magnitude){
        if (magnitude==0) return Color.TRANSPARENT;
        double green = (255 - magnitude) < 0 ? 0 : (255-magnitude);
        double red = (magnitude) > 255 ? 255 : (magnitude);
        return Color.rgb((int)red,(int) green,0, 0.7);
    }

    public Group getG() {
        return g;
    }
}
