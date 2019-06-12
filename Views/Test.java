package Views;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Test extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Register pane=new Register();
        Scene scene=new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
