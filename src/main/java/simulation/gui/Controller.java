package simulation.gui;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import simulation.physics.engine.FieldController;
import simulation.physics.particle.Particle;

import java.util.HashMap;
import java.util.Map;


public class Controller {
    private Map<Circle,Particle> unmoveables = new HashMap<>();
    private Group fieldVec = new Group();
    private Group magneticVec = new Group();
    private Group particles = new Group();
    private Charge charge = new Charge();
    private Group level1Group = new Group();
    private Group level2Group = new Group();
    private Group level3Group = new Group();
    private Group gate = new Group();
    private Updater updater = new Updater(this);
    private boolean fieldLines;
    private boolean magneticLines;
    private FieldController field;
    private Text collisionT = new Text(100, 100, "Collision detected!");
    private Text winT = new Text(100, 100, "Victory!");
    private int currentLvl = 0;
    private Circle circle = new Circle();
    ToggleGroup toggleGroup = new ToggleGroup();
    @FXML
    private Line line1,line2,line3,lineb1,lineb2,lineb3,linewin,line_2,line2_1,line2_2;
    @FXML
    private Group radios;
    @FXML
    private RadioButton buttonLevel1,buttonLevel2,buttonLevel3,buttonPractice;
    @FXML
    private Button buttonStart;
    @FXML
    private Pane pane;
    @FXML
    private Button addProton;
    @FXML
    public void addProtonClick(){
        Particle p = new Particle(Particle.ELECTRON_CHARGE , 1, 1);
        Circle c = new Circle();
        setUpCharge(c, p);
        c.setFill(Color.RED);
    }
    @FXML
    private Button addElectron;
    @FXML
    public void addElectronClick(){
        Particle p = new Particle(-Particle.ELECTRON_CHARGE , 1, 1);
        Circle c = new Circle();
        setUpCharge(c, p);
        c.setFill(Color.BLUE);
    }
    @FXML
    private Button buttonClear;
    @FXML
    public void buttonClearClick(){
        charge.getCircle().setLayoutY(100);
        charge.getCircle().setLayoutX(100);
        field.getParticles().clear();
        updateElectricField();
        unmoveables.clear();
        particles.getChildren().clear();
        field.reset();
        field.setBallXY(100,100);
        collisionT.setVisible(false);
        winT.setVisible(false);
        onStop();
    }

    @FXML
    public void initialize() {
        field = new FieldController(Particle.ELECTRON_MASS/22);
        charge.setCircle(100f,100f, circle);
        updater.setC(charge);
        updater.setField(field);
        updater.setParticles(unmoveables);
        pane.getChildren().addAll(circle, magneticVec, fieldVec,particles,collisionT,winT,level1Group,level2Group,level3Group,gate);
        level3Group.getChildren().addAll(line1,line2,line3);
        level2Group.getChildren().addAll(line2_1,line2_2);
        level1Group.getChildren().addAll(line_2);
        gate.getChildren().addAll(lineb1,lineb2,lineb3,linewin);
        collisionT.setFont(new Font(20));
        winT.setFont(new Font(20));
        collisionT.setVisible(false);
        winT.setVisible(false);
        setUnvisible();
        setGate(false);
        buttonLevel1.setToggleGroup(toggleGroup);
        buttonLevel2.setToggleGroup(toggleGroup);
        buttonLevel3.setToggleGroup(toggleGroup);
        buttonPractice.setToggleGroup(toggleGroup);


    }
    @FXML
    public void onStart(){
        new Thread(field).start();
        new Thread(updater).start();
        collisionT.setVisible(false);
    }
    @FXML
    public void onStop(){
        updater.setRunning(false);
        field.setRunning(false);
    }

    @FXML
    public void lvl1Clicked(){
        setUnvisible();
        setGate(true);
        for (Node n : level1Group.getChildren())
            n.setVisible(true);
        linewin.setVisible(false);
        currentLvl = 1;
    }
    public void lvl2Clicked(){
        setUnvisible();
        setGate(true);
        for (Node n : level2Group.getChildren())
            n.setVisible(true);
        linewin.setVisible(false);
        currentLvl = 2;
    }
    public void lvl3Clicked(){
        setUnvisible();
        setGate(true);
        for (Node n : level3Group.getChildren())
            n.setVisible(true);
        linewin.setVisible(false);
        currentLvl = 3;
    }
    public void practiceClicked(){
        setUnvisible();
        setGate(false);
        currentLvl=0;
    }


    private void setUnvisible(){
        for(Node n: level3Group.getChildren()){
            n.setVisible(false);
        }
        for(Node n: level2Group.getChildren()){
            n.setVisible(false);
        }
        for(Node n: level1Group.getChildren()){
            n.setVisible(false);
        }
    }
    private void setGate(Boolean stance){
        for(Node n: gate.getChildren()){
            n.setVisible(stance);
        }
    }
    @FXML
    public void showFieldClicked(){
        showField();
    }

    @FXML
    public void showMagneticFieldClicked(){
        showMagneticField();
    }

    public void showField(){
        if (fieldLines){
            fieldLines=false;
            fieldVec.getChildren().clear();
        }
        else{
            fieldLines = true;
            updateElectricField();

        }
    }
    public void updateElectricField(){
        if (fieldLines) {
            fieldVec.getChildren().clear();
            for (int i = 0; i < 18; i++) {
                for (int j = 0; j < 12; j++) {
                    Arrow a = new Arrow(field.getElectricFieldAt(50 * i, 50 * j).getX(), field.getElectricFieldAt(50 * i, 50 * j).getY());
                    a.getG().setTranslateX(50 * i);
                    a.getG().setTranslateY(50 * j);
                    fieldVec.getChildren().add(a.getG());
                }
            }
        }
    }

    public void showMagneticField() {
        if (magneticLines){
            magneticLines=false;
            magneticVec.getChildren().clear();
        }
        else{
            magneticLines = true;
            updateMagneticField();
        }
    }

    public void updateMagneticField() {
        if (magneticLines) {
            magneticVec.getChildren().clear();
            for (int i = 0; i < 17; i++) {
                for (int j = 1; j < 11; j++) {
                    VectorZ vz = new VectorZ(50 * i + 25, 50 * j + 25, field.getMagneticFieldAt(50 * i + 25, 50 * j + 25));
                    magneticVec.getChildren().add(vz.getG());
                }
            }
        }
    }

    public void checkCollision(){
        if (currentLvl==1) {
            for (Node n : level1Group.getChildren()
            ) {
                if (n != linewin) {
                    if (charge.getCircle().getBoundsInParent().intersects(n.getBoundsInParent())) {
                        gameOver();
                    }
                } else {
                    if (charge.getCircle().getBoundsInParent().intersects(n.getBoundsInParent())) {
                        victory();
                    }
                }
            }
        }
        if (currentLvl==2) {
            for (Node n : level2Group.getChildren()
            ) {
                if (n != linewin) {
                    if (charge.getCircle().getBoundsInParent().intersects(n.getBoundsInParent())) {
                        gameOver();
                    }
                } else {
                    if (charge.getCircle().getBoundsInParent().intersects(n.getBoundsInParent())) {
                        victory();
                    }
                }
            }
        }
        if (currentLvl==3) {
            for (Node n : level3Group.getChildren()
            ) {
                if (n != linewin) {
                    if (charge.getCircle().getBoundsInParent().intersects(n.getBoundsInParent())) {
                        gameOver();
                    }
                } else {
                    if (charge.getCircle().getBoundsInParent().intersects(n.getBoundsInParent())) {
                        victory();
                    }
                }
            }
        }
        if (currentLvl!=0){
            for (Node n : gate.getChildren()
            ) {
                if (n != linewin) {
                    if (charge.getCircle().getBoundsInParent().intersects(n.getBoundsInParent())) {
                        gameOver();
                    }
                } else {
                    if (charge.getCircle().getBoundsInParent().intersects(n.getBoundsInParent())) {
                        victory();
                    }
                }
            }
        }
    }

    private void gameOver(){
        onStop();
        collisionT.setVisible(true);
    }
    private void victory(){
        onStop();
        winT.setVisible(true);
    }

    private double orgSceneX, orgSceneY;
    private double orgTranslateX, orgTranslateY;

    private void setUpCharge(Circle c, Particle p){
        final double startX = 450;
        final double startY = 100;
        field.addParticle(p);
        c.setLayoutX(0);
        c.setLayoutY(0);
        c.setTranslateX(startX);
        c.setTranslateY(startY);
        p.getPosition().setX(startX);
        p.getPosition().setY(startY);
        c.setRadius(6.5);
        particles.getChildren().addAll(c);
        c.setOnMousePressed(e -> {
            if (e.getButton() == MouseButton.SECONDARY){
                Button set = new Button("set");
                TextField vx = new TextField("xVel");
                vx.setPrefWidth(50);
                TextField vy = new TextField("yVel");
                vy.setPrefWidth(50);
                HBox hBox = new HBox(vx, vy, set);
                Pane velPane = new AnchorPane(hBox);
                velPane.setTranslateX(e.getSceneX() + 10);
                velPane.setTranslateY(e.getSceneY());
                pane.getChildren().add(velPane);
                set.setOnMouseClicked(ev -> {
                    onStop();
                    p.setMoving(Double.parseDouble(vx.getText()), Double.parseDouble(vy.getText()));
                    pane.getChildren().remove(velPane);
                    updateMagneticField();
                });

            }
            orgSceneX = e.getSceneX();
            orgSceneY = e.getSceneY();
            orgTranslateX = ((Circle)(e.getSource())).getTranslateX();
            orgTranslateY = ((Circle)(e.getSource())).getTranslateY();
        });
        c.setOnMouseDragged(e -> {
            double offsetX = e.getSceneX() - orgSceneX;
            double offsetY = e.getSceneY() - orgSceneY;
            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;
            Circle source = ((Circle)(e.getSource()));
            source.setTranslateX(newTranslateX);
            source.setTranslateY(newTranslateY);
            unmoveables.get(source).getPosition().setX(source.getTranslateX());
            unmoveables.get(source).getPosition().setY(source.getTranslateY());
            updateElectricField();
            updateMagneticField();
        });
        updateElectricField();
        unmoveables.put(c,p);
    }

}
