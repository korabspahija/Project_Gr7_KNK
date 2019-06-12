import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import Views.*;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        LogInView login=new LogInView();
        Scene scene=new Scene(login,1000,700);
        primaryStage.setScene(scene);
        primaryStage.show();

        login.getBtnLogin().setOnAction(event -> {

        });

        login.getLblSignup().setOnMouseClicked(event -> {
            Stage signUpStage=new Stage();
            Scene signUpScene=new Scene(new Register());
            signUpStage.setScene(signUpScene);
            signUpStage.show();
            primaryStage.hide();
        });

        login.getLblGuest().setOnMouseClicked(event -> {
            Stage guestStage=new Stage();
            Scene guestScene=new Scene(new RegularUI());
            guestStage.setScene(guestScene);
            guestStage.show();
            primaryStage.hide();
        });



    }
}
