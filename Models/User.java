package Models;

import Helpers.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.lang.String;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;


public class User {
    private int id;
    private String companyName;
    private String username;
    private String email;


    public User(String companyName, String email, String username){
        this.id=id;
        this.companyName=companyName;
        this.username=username;
        this.email=email;
    }

    public int getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }


    public String getEmail() {
        return email;
    }


    public String getUsername() {
        return username;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

/////////////////UPDATE/////////////////////////////
//    public static boolean updateRoute(String scheduleId, int id){
//        String query="UPDATE routes SET schedule_id = (SELECT timetable.id FROM timetable WHERE time = ?) WHERE routes.id=?";
//
//        try {
//            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query);
//
//            preparedStatement.setTime(1, java.sql.Time.valueOf(scheduleId));
//            preparedStatement.setInt(2, id);
//
//            System.out.println(java.sql.Time.valueOf(scheduleId));
//
//            return (preparedStatement.executeUpdate()>0);
//        }catch (SQLException ex){
////        	System.out.println("A");
//            ex.printStackTrace();
//            return false;
//        }
//    }

    public static List<User> getRoutes(){

        List<User> routeList=new ArrayList<>();

        String query= "select companies.name, users.email, users.username  from companies inner JOIN users where manager_id=users.id ;";
        try {
            PreparedStatement preparedStatement=DBConnection.getConnection().prepareStatement(query);
            ResultSet resultSet=preparedStatement.executeQuery();

            while (resultSet.next()){
                User route=new User(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3));

                routeList.add(route);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }

        return routeList;
    }

    public static void showRoutes(TableView tv) {
        List<User> routes = User.getRoutes();

        ObservableList<User> routesList = FXCollections.observableArrayList();

        for(int i = 0; i < routes.size(); i++) {
            routesList.add(routes.get(i));
        }

        tv.setItems(routesList);
    }




}
