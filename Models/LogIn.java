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

    public static int loggingIn(String username, String password){
        String query = "Select role_id from users where username = ? and password = ?";
        try {
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2,password);

            ResultSet result = preparedStatement.executeQuery();

            if(result.next()) {
                return result.getInt(1);
            } else {
                return 0;
            }
        }catch(SQLException ex){
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Database problem");
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
            System.exit(0);
            return 0;
        }
    }

    public static int getUserIdByUsername(String username){
        String query="SELECT id FROM users WHERE username=?";
        try{
            PreparedStatement preparedStatement=DBConnection.getConnection().prepareStatement(query);
            preparedStatement.setString(1,username);
            ResultSet resultSet=preparedStatement.executeQuery();
            resultSet.beforeFirst();
            resultSet.next();
            return resultSet.getInt(1);
        }catch (SQLException ex){
            ex.printStackTrace();
            return 10;
        }
    }


}

