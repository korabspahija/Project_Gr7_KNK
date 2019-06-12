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


public class Timetable {
	private int id;
    private String companyName;
    private String startCity;
    private String endCity;
    private String startHour;
    
    public Timetable(int id,double price,String companyName,String startCity,String endCity,String schedule){
        this.id=id;
        this.companyName=companyName;
        this.startCity=startCity;
        this.endCity=endCity;
        this.startHour=schedule;
    }
    
    public int getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    
    public String getStartCity() {
        return startCity;
    }


    public String getEndCity() {
        return endCity;
    }


    public String getStartHour() {
        return startHour;
    }

    public void setSchedule(String startHour) {
        this.startHour = startHour;
    }
    
    
    
    public static boolean updateRoute(String scheduleId, int id){
        String query="UPDATE routes SET schedule_id = (SELECT timetable.id FROM timetable WHERE time = ?) WHERE routes.id=?";

        try {
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query);
            
            preparedStatement.setTime(1, java.sql.Time.valueOf(scheduleId));
            preparedStatement.setInt(2, id);
            
            System.out.println(java.sql.Time.valueOf(scheduleId));
            
            return (preparedStatement.executeUpdate()>0);
        }catch (SQLException ex){
//        	System.out.println("A");
            ex.printStackTrace();
            return false;
        }
    }
    
    public static List<Timetable> getRoutes(){
    	
        List<Timetable> routeList=new ArrayList<>();

        String query= "SELECT routes.id,price,companies.name,city1.name,city2.name,time\n" +
                "FROM routes NATURAL JOIN companies NATURAL JOIN timetable ,cities as city1,cities as city2 WHERE start_city=city1.id and end_city=city2.id";
        try {
            PreparedStatement preparedStatement=DBConnection.getConnection().prepareStatement(query);
            ResultSet resultSet=preparedStatement.executeQuery();

            while (resultSet.next()){
                Timetable route=new Timetable(resultSet.getInt(1),resultSet.getDouble(2),resultSet.getString(3),
                		resultSet.getString(4),resultSet.getString(5),resultSet.getString(6));
                routeList.add(route);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }

        return routeList;
    }

    public static void showRoutes(TableView tv) {
        List<Timetable> routes = Timetable.getRoutes();

        ObservableList<Timetable> routesList = FXCollections.observableArrayList();

        for(int i = 0; i < routes.size(); i++) {
            routesList.add(routes.get(i));
        }

        tv.setItems(routesList);
    }




}
