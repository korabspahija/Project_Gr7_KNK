package Models;

import Helpers.DBConnection;
import javafx.scene.control.Alert;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogIn {

    private int id ;
    private String username;
    private String password;
    private int role_id;

    public LogIn(int id, String username, String password, int role_id) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role_id = role_id;
    }

    public static boolean loggingIn(String username, String password){
        String query = "Select role_id from users where username = ? and password = ?";
        try {
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2,password);

            ResultSet result = preparedStatement.executeQuery();

            if(result.next()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Login result");
                alert.setHeaderText(null);
                alert.setContentText("You have logged in!");
                alert.showAndWait();
                return true;
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Login result");
                alert.setHeaderText(null);
                alert.setContentText("Email or password is wrong!");
                alert.showAndWait();
                return false;

            }
        }catch(SQLException ex){
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Database problem");
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
            System.exit(0);
            return false;
        }
    }

}

