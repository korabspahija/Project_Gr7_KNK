package Main;

import Models.LogIn;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.scene.Scene;
import Views.*;

public class Test extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        LogInView login=new LogInView();
        Scene scene=new Scene(login,1000,700);
        primaryStage.setScene(scene);
        primaryStage.show();

        scene.setOnKeyPressed(event -> {
            if(event.getCode()== KeyCode.ENTER){
                if(LogIn.loggingIn(login.getTfUsername().getText(),login.getPfPassword().getText())==1){
                    Stage adminStage=new Stage();
                    Scene adminScene=new Scene(new AdminView(adminStage),1200,700);
                    adminStage.setScene(adminScene);
                    adminStage.setTitle("Admin");
                    adminStage.show();
                    primaryStage.hide();
                }else if(LogIn.loggingIn(login.getTfUsername().getText(),login.getPfPassword().getText())==2) {
                    int userId=LogIn.getUserIdByUsername(login.getTfUsername().getText());
                    Stage managerStage = new Stage();
                    Scene managerScene = new Scene(new CompaniesUI(userId,managerStage));
                    managerStage.setScene(managerScene);
                    managerStage.show();
                    primaryStage.hide();
                }else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Login result");
                    alert.setHeaderText(null);
                    alert.setContentText("Email or password is wrong!");
                    alert.showAndWait();
                }
            }
        });

        login.getBtnLogin().setOnAction(event -> {
            if(LogIn.loggingIn(login.getTfUsername().getText(),login.getPfPassword().getText())==1){
                Stage adminStage=new Stage();
                Scene adminScene=new Scene(new AdminView(adminStage),1200,700);
                adminStage.setScene(adminScene);
                adminStage.setTitle("Admin");
                adminStage.show();
                primaryStage.hide();
            }else if(LogIn.loggingIn(login.getTfUsername().getText(),login.getPfPassword().getText())==2) {
                int userId=LogIn.getUserIdByUsername(login.getTfUsername().getText());
                Stage managerStage = new Stage();
                Scene managerScene = new Scene(new CompaniesUI(userId,managerStage));
                managerStage.setScene(managerScene);
                managerStage.show();
                primaryStage.hide();
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Login result");
                alert.setHeaderText(null);
                alert.setContentText("Email or password is wrong!");
                alert.showAndWait();
            }
        });

        login.getLblSignup().setOnMouseClicked(event -> {
            Stage signUpStage=new Stage();
            Scene signUpScene=new Scene(new Register(signUpStage));
            signUpStage.setScene(signUpScene);
            signUpStage.show();
            primaryStage.hide();
        });

        login.getLblGuest().setOnMouseClicked(event -> {
            Stage guestStage=new Stage();
            Scene guestScene=new Scene(new RegularUI(guestStage));
            guestStage.setScene(guestScene);
            guestStage.show();
            primaryStage.hide();
        });

    }
}
