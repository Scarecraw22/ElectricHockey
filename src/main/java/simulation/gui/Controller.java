package simulation.gui;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import simulation.physics.engine.FieldController;
import simulation.physics.particle.Particle;

import java.util.HashMap;
import java.util.Map;


public class Controller {
    private Map<Circle,Particle> unmoveables = new HashMap<>();
    private Group fieldVec = new Group();
    private Group particles = new Group();
    private Charge charge = new Charge();
    private Updater updater = new Updater();
    private boolean fieldLines;
    private FieldController field;

    @FXML
    private Button buttonStart;
    @FXML
    private Pane pane;
    @FXML
    private Button addProton;
    @FXML
    public void addProtonClick(){
        Particle p = new Particle(Particle.ELECTRON_CHARGE , 1, 1);
        field.addParticle(p);
        Circle c = new Circle();
        c.setLayoutX(0);
        c.setLayoutY(0);
        c.setRadius(5);
        particles.getChildren().addAll(c);
        c.setOnMousePressed(circleOnMousePressedEventHandler);
        c.setOnMouseDragged(circleOnMouseDraggedEventHandler);
        unmoveables.put(c,p);
        c.setFill(Color.RED);
    }
    @FXML
    private Button addElectron;
    @FXML
    public void addElectronClick(){
        Particle p = new Particle(-Particle.ELECTRON_CHARGE , 1, 1);
        field.addParticle(p);
        Circle c = new Circle();
        c.setLayoutX(0);
        c.setLayoutY(0);
        c.setRadius(5);
        particles.getChildren().addAll(c);
        c.setOnMousePressed(circleOnMousePressedEventHandler);
        c.setOnMouseDragged(circleOnMouseDraggedEventHandler);
        unmoveables.put(c,p);
        c.setFill(Color.BLUE);


    }
    @FXML
    private Button buttonClear;
    @FXML
    public void buttonClearClick(){
        field.getParticles().clear();
        updateField();
        unmoveables.clear();
        particles.getChildren().clear();
        onStop();
    }

    @FXML
    public void initialize() {
        field = new FieldController(Particle.ELECTRON_MASS/22);

        charge.setCircle(100f,100f);
        updater.setC(charge);
        updater.setField(field);
        pane.getChildren().addAll(charge.getCircle(),fieldVec,particles);


    }
    @FXML
    public void onStart(){
        new Thread(field).start();
        new Thread(updater).start();

    }
    @FXML
    public void onStop(){
        updater.setRunning(false);
        field.setRunning(false);
    }
    @FXML
    public void showFieldClicked(){
        showField();
    }

    public void showField(){
        if (fieldLines){
            fieldLines=false;
            fieldVec.getChildren().clear();
        }
        else{
            fieldLines = true;
            updateField();

        }
    }
    public void updateField(){
        if (fieldLines) {
            fieldVec.getChildren().clear();
            for (int i = 0; i < 18; i++) {
                for (int j = 0; j < 12; j++) {
                    Arrow a = new Arrow(field.getFieldAt(50 * i, 50 * j).getX(), field.getFieldAt(50 * i, 50 * j).getY());
                    a.getG().setTranslateX(50 * i);
                    a.getG().setTranslateY(50 * j);
                    fieldVec.getChildren().add(a.getG());
                }
            }
        }
    }


    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;
    EventHandler<MouseEvent> circleOnMousePressedEventHandler =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    orgSceneX = t.getSceneX();
                    orgSceneY = t.getSceneY();
                    orgTranslateX = ((Circle)(t.getSource())).getTranslateX();
                    orgTranslateY = ((Circle)(t.getSource())).getTranslateY();
                }
            };

    EventHandler<MouseEvent> circleOnMouseDraggedEventHandler =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    double offsetX = t.getSceneX() - orgSceneX;
                    double offsetY = t.getSceneY() - orgSceneY;
                    double newTranslateX = orgTranslateX + offsetX;
                    double newTranslateY = orgTranslateY + offsetY;

                    ((Circle)(t.getSource())).setTranslateX(newTranslateX);
                    ((Circle)(t.getSource())).setTranslateY(newTranslateY);
                    unmoveables.get((Circle)(t.getSource())).getPosition().setX(1+newTranslateX);
                    unmoveables.get((Circle)(t.getSource())).getPosition().setY(1+newTranslateY);
                    updateField();

                }
            };

}
