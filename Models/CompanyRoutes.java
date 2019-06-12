package Models;

import Helpers.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyRoutes {
    private int id;
    private double price;
    private String companyName;
    private String startCity;
    private String endCity;
    private String schedule;

    public CompanyRoutes(double price,String companyName,String startCity,String endCity,String schedule){
        this.price=price;
        this.companyName=companyName;
        this.startCity=startCity;
        this.endCity=endCity;
        this.schedule=schedule;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getStartCity() {
        return startCity;
    }

    public void setStartCity(String startCity) {
        this.startCity = startCity;
    }

    public String getEndCity() {
        return endCity;
    }

    public void setEndCity(String endCity) {
        this.endCity = endCity;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }




    public static List<CompanyRoutes> getRoutes(String startCity, String endCity,int userId){
        List<CompanyRoutes> routeLists=new ArrayList<>();

        String query = " SELECT  r.price, c.name, t.time  FROM timetable t  JOIN  routes r  JOIN companies c JOIN users u" +
        " WHERE r.company_id = c.id AND t.id = r.schedule_id AND c.manager_id = u.id AND" +
                " r.start_city = (select c1.id from cities c1 where c1.name = ?) AND r.end_city= (select c2.id from cities c2 where c2.name = ?) and u.id = ?";



        try {
            PreparedStatement preparedStatement=DBConnection.getConnection().prepareStatement(query);
            preparedStatement.setString(1,startCity);
            preparedStatement.setString(2, endCity);
            preparedStatement.setInt(3,userId);
            ResultSet resultSet=preparedStatement.executeQuery();


            while (resultSet.next()){

                CompanyRoutes routes = new CompanyRoutes(resultSet.getDouble(1),resultSet.getString(2),startCity,endCity, resultSet.getString(3));

                routeLists.add(routes);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }

        return routeLists;
    }


    public static void showRoutes(TableView tableView, String startCity, String endCity, int userId){
        List<CompanyRoutes> routes = CompanyRoutes.getRoutes(startCity,endCity, userId);

        ObservableList<CompanyRoutes> routesList = FXCollections.observableArrayList();
        for (int i = 0 ; i< routes.size(); i++){

            routesList.add(routes.get(i));

        }

        tableView.setItems(routesList);


    }


    public static boolean insertRoute(double price,String startCity, String endCity, String schedule){

        String query = "INSERT INTO routes (company_id, price,start_city,end_city,schedule_id) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,1);
            preparedStatement.setDouble(2,price);
            preparedStatement.setInt(3,getIdByNameCities(startCity));
            preparedStatement.setInt(4,getIdByNameCities(endCity));
            preparedStatement.setInt(5,getIdByTime(schedule));

            return (preparedStatement.executeUpdate() > 0);
        }catch (SQLException ex){
            System.out.println("ooof te inserti ..." + ex);
            return false;
        }

    }
    public static boolean updateRoute(double price,String startCity, String endCity, String schedule,int id){
        String query = "UPDATE routes SET price = ?, start_city = ?,end_city = ?, schedule_id = ? where id = ? ";
        try {
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query);
            preparedStatement.setDouble(1,price);
            preparedStatement.setInt(2,getIdByNameCities(startCity));
            preparedStatement.setInt(3,getIdByNameCities(endCity));
            preparedStatement.setInt(4,getIdByTime(schedule));
            preparedStatement.setInt(5,id);

            return (preparedStatement.executeUpdate()>0);

        }catch (SQLException ex){
            System.out.println("Oooof te Update " + ex);
            return false;
        }
    }
    public static boolean deleteRoute(int id) {
        String query = "DELETE FROM routes WHERE id = ?";
        try {
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);

            return (preparedStatement.executeUpdate() > 0);
        } catch (SQLException ex) {
            System.out.println("Ooof te Delete " + ex);
            return false;
        }
    }


    public static int getIdByNameCities(String name){
        String query="SELECT id FROM cities WHERE name=?";
        try{
            PreparedStatement preparedStatement=DBConnection.getConnection().prepareStatement(query);
             preparedStatement.setString(1,name);

            ResultSet resultSet=preparedStatement.executeQuery();
            resultSet.beforeFirst();
            resultSet.next();
            return resultSet.getInt(1);
        }catch (SQLException ex){
            ex.printStackTrace();
            return 0;
        }
    }


    public static int getIdByTime(String name) {

        String query = "SELECT id FROM timetable WHERE time = ?";
        try {

            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query);

            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.beforeFirst();
            resultSet.next();
            return  resultSet.getInt(1);
        } catch (SQLException ex) {
            System.out.println("ooof te getIdByTime... " + ex);
            return 0;

//
        }
    }


    public static void showSchedule(ComboBox<String> cbo) {
    String query = "Select time from timetable";
    try{
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            cbo.getItems().add(resultSet.getString(1));
        }
    }catch (SQLException ex){

        System.out.println("ooof te show schedule ... "+ ex);
    } }




    public static List<CompanyRoutes> getRoutes(String startCity, String endCity){
        List<CompanyRoutes> routeLists=new ArrayList<>();

        String query = " SELECT  r.price, c.name, t.time,  FROM timetable t  JOIN  routes r  JOIN companies c JOIN users u" +
                "WHERE r.company_id = c.id AND t.id = r.schedule_id AND c.manager_id = u.id AND" +
                "r.start_city = (select c1.id from cities c1 where c1.name = ?) AND r.end_city= (select c2.id from cities c2 where c2.name = ?)";



        try {
            PreparedStatement preparedStatement=DBConnection.getConnection().prepareStatement(query);
            preparedStatement.setString(1,startCity);
            preparedStatement.setString(2, endCity);
            ResultSet resultSet=preparedStatement.executeQuery();


            while (resultSet.next()){

                CompanyRoutes routes = new CompanyRoutes(resultSet.getDouble(1),resultSet.getString(2),startCity,endCity, resultSet.getString(3));

                routeLists.add(routes);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }

        return routeLists;
    }


    public static void showRoutes(TableView tableView, String startCity, String endCity){
        List<CompanyRoutes> routes = CompanyRoutes.getRoutes(startCity,endCity);

        ObservableList<CompanyRoutes> routesList = FXCollections.observableArrayList();
        for (int i = 0 ; i< routes.size(); i++){

            routesList.add(routes.get(i));

        }

        tableView.setItems(routesList);


    }

}

