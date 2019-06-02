package simulation.gui;

import javafx.scene.Group;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;

import static java.lang.Math.E;

public class Arrow  {
    private  static final double MAX=20;
    private  static final double MIN=-20;
    private static final double arrowLength = 20;
    private static final double arrowWidth = 7;
    Group g=new Group();

    public Group getG() {
        return g;
    }

    public Arrow(){
        Line l = new Line(0,10,20,10);
       /* Polygon p = new Polygon(
                18,8,
                18,12,
                10,20);*/
        Polygon p = new Polygon();
        p.getPoints().addAll(new Double[]{
                15.0,5.0,
                15.0,15.0,
                25.0,10.0});
        /*p.getPoints().addAll(new Double[]{
                0.0, 0.0,
                20.0, 10.0,
                10.0, 20.0 });*/
        g.getChildren().addAll(p,l);

    }
    public Arrow(double x,double y){
        double x1=limit(x*Math.pow(10,14));
        double y1=limit(y*Math.pow(10,14));
        Line l = new Line(0,0,
                x1,y1);

        g.getChildren().addAll(l);
    }
    private double limit(double a){
        return (a > MAX ) ? MAX : (a < MIN ? MIN: a);
    }
    public static double sigmoid(double x) {
        return (1/( 1 + Math.pow(Math.E,(-1*x))) - 0.5);
    }

}
