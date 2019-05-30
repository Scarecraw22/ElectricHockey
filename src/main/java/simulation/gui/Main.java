package simulation.gui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import simulation.physics.engine.FieldController;
import simulation.physics.particle.Particle;

public class Main extends Application {
    private final int primaryWidth = 900;
   private final int primaryHeight = 600;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("sample.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, primaryWidth, primaryHeight));
        primaryStage.setOnCloseRequest(e -> ((Controller) fxmlLoader.getController()).onStop());
        primaryStage.show();

    }


    public static void main(String[] args) {

        launch(args);

    }

}
