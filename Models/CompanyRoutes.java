package Models;

import Helpers.DBConnection;
import Views.RoutesView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
//        this.id=id;
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




    public static List<CompanyRoutes> getRoutes(String startCity, String endCity){
        List<CompanyRoutes> routeLists=new ArrayList<>();

        String query = "SELECT  r.price, c.name, t.time FROM timetable t  JOIN  routes r  JOIN companies c " +
                "Where  r.company_id = c.id and r.schedule_id = t.id and r.start_city = (select c1.id from cities c1 where c1.name = ?) AND r.end_city = (select c2.id from cities c2 where c2.name = ?)";

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
