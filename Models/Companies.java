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


public class Companies {
    private int id;
    private String companyName;
    private String username;
    private int no_buses;


    public Companies(String companyName, String username, int no_buses){
        this.id=id;
        this.companyName=companyName;
        this.username=username;
        this.no_buses=no_buses;
    }

    public int getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }


    public int getNo_buses() {
        return no_buses;
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

    public void setNo_buses(int no_buses) {
        this.no_buses = no_buses;
    }


    public static List<Companies> getRoutes(){

        List<Companies> routeList=new ArrayList<>();

        String query= "select companies.name,  users.username, companies.no_buses  from companies inner JOIN users where manager_id=users.id ;";
        try {
            PreparedStatement preparedStatement=DBConnection.getConnection().prepareStatement(query);
            ResultSet resultSet=preparedStatement.executeQuery();

            while (resultSet.next()){
                Companies route=new Companies(resultSet.getString(1),resultSet.getString(2),resultSet.getInt(3));

                routeList.add(route);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }

        return routeList;
    }


    public static void showRoutes(TableView tv) {
        List<Companies> routes = Companies.getRoutes();

        ObservableList<Companies> routesList = FXCollections.observableArrayList();

        for(int i = 0; i < routes.size(); i++) {
            routesList.add(routes.get(i));
        }

        tv.setItems(routesList);
    }


    public static boolean deleteRecord(String companyName) {
        String query = "Delete from companies where companies.name=?";

        try {
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, companyName);
            return (preparedStatement.executeUpdate() > 0);
        } catch(SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static int getIdByName(String name){
        String query="SELECT id FROM companies WHERE name=?";
        try {
            PreparedStatement preparedStatement=DBConnection.getConnection().prepareStatement(query);
            preparedStatement.setString(1,name);
            ResultSet resultSet=preparedStatement.executeQuery();
            resultSet.beforeFirst();
            resultSet.next();
            return resultSet.getInt(1);
        }catch (SQLException ex){
            ex.printStackTrace();
            return 3;
        }
    }

    public static int getCompanyId(int userId){
        String query= "SELECT id FROM companies WHERE manager_id=?";
        try{
            PreparedStatement preparedStatement=DBConnection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,userId);
            ResultSet resultSet=preparedStatement.executeQuery();
            resultSet.beforeFirst();
            resultSet.next();
            return resultSet.getInt(1);
        }catch (SQLException ex){
            ex.printStackTrace();
            return 0;
        }
    }
}




