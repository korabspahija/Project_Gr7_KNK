package Models;

import Helpers.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Route {
    private int id;
    private double price;
    private String companyName;
    private String startCity;
    private String endCity;
    private String schedule;

    public Route(int id,double price,String companyName,String startCity,String endCity,String schedule){
        this.id=id;
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

    public static boolean addRoute(double price,int companyId,int startCityId,int endCityId,int scheduleId){
        String query="INSERT INTO routes (price,company_id,start_city,end_city,schedule_id) VALUES(?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement= DBConnection.getConnection().prepareStatement(query);
            preparedStatement.setDouble(1,price);
            preparedStatement.setInt(2,companyId);
            preparedStatement.setInt(3,startCityId);
            preparedStatement.setInt(4,endCityId);
            preparedStatement.setInt(5,scheduleId);

            return(preparedStatement.executeUpdate()>0);
        }catch (SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean updateRoute(int id,double price,int companyId,int startCityId,int endCityId,int scheduleId){
        String query="UPDATE routes SET price=?,companyId=?,startCityId=?,endCityId=?,scheduleId=? WHERE id=?";

        try {
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query);

            preparedStatement.setDouble(1, price);
            preparedStatement.setInt(2, companyId);
            preparedStatement.setInt(3, startCityId);
            preparedStatement.setInt(4, endCityId);
            preparedStatement.setInt(5, scheduleId);
            preparedStatement.setInt(6,id);

            return (preparedStatement.executeUpdate()>0);
        }catch (SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean deleteRoute(int id){
        String query="DELETE FROM routes WHERE id=?";
        try {
            PreparedStatement preparedStatement=DBConnection.getConnection().prepareStatement(query);

            preparedStatement.setInt(1,id);
            return (preparedStatement.executeUpdate()>0);
        }catch (SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }

    public static List<Route> getRoutes(){
        List<Route> routeList=new ArrayList<>();

        String query="SELECT routes.id,price,companies.name,city1.name,city2.name,time\n" +
                "FROM routes NATURAL JOIN companies NATURAL JOIN timetable ,cities as city1,cities as city2 WHERE start_city=city1.id and end_city=city2.id";

        try {
            PreparedStatement preparedStatement=DBConnection.getConnection().prepareStatement(query);
            ResultSet resultSet=preparedStatement.executeQuery();

            while (resultSet.next()){
                Route route=new Route(resultSet.getInt(1),resultSet.getDouble(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6));
                routeList.add(route);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }

        return routeList;
    }
}
