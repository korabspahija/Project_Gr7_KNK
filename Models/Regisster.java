package per_projekt;

import per_projekt.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.lang.String;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;


public class Regisster {
    
    
    public static boolean insert(String firstname, String lastname, String username, String password, String email){
        String query="INSERT INTO users(firstname, lastname, username, password,email,role_id) \n" + 
        		"VALUES (?,?,?,?,?,?);";
        try {
            PreparedStatement preparedStatement=DBConnection.getConnection().prepareStatement(query);
            
            preparedStatement.setString(1, firstname);
            preparedStatement.setString(2, lastname);
            preparedStatement.setString(3, username);
            preparedStatement.setString(4, password);
            preparedStatement.setString(5, email);
            preparedStatement.setInt(6, 2);
            
            return (preparedStatement.executeUpdate()>0);
        }catch (SQLException ex){
            ex.printStackTrace();
            return false;
        }
        
        
    
    }
    
//    public static int getRoleid(String username) {
//    	
//    	String query = "Select role_id from users where username=?";
//    	 try {
//             PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query);
//             preparedStatement.setString(1, username);
//             ResultSet result = preparedStatement.executeQuery();
//             String str = result.getString("role_id");
//             int quan = Integer.parseInt(str);
//             return quan;
//             }catch(SQLException ex){
//                 ex.printStackTrace();
//                 return 0;}    	
//    }
    
 
    
    
    
}
