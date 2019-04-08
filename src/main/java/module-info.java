module simulatioin {
    requires transitive javafx.base;
    requires transitive javafx.graphics;
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires transitive java.desktop;
    requires java.datatransfer;

//    opens simulation.gui javafx.fxml;

    exports simulation.gui to javafx.fxml, javafx.graphics;

}
