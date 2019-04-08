package simulation.gui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private final int primaryWidth = 900;
    private final int primaryHeight = 700;


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, primaryWidth, primaryHeight));


        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
