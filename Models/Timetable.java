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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;


public class Timetable {
	private int id;
	private int schid;
    private String companyName;
    private String startCity;
    private String endCity;
    private String startHour;
    
    public Timetable(int id,int schid,String companyName,String startCity,String endCity,String schedule){
        this.id=id;
        this.schid=schid;
        this.companyName=companyName;
        this.startCity=startCity;
        this.endCity=endCity;
        this.startHour=schedule;
    }
    
    
    public Timetable(String time) {
    	this.startHour = time;
    }
    
    public int getId() {
        return id;
    }
    
    public int getSchid() {
		return schid;
	}

	public void setSchid(int schid) {
		this.schid = schid;
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
    	String query = "UPDATE routes SET routes.schedule_id = (SELECT timetable.id FROM timetable WHERE time = ?) WHERE routes.id=?;";

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
    
    public static boolean updateDepartureHour(String currHour, String depHour) {
    	String query = "UPDATE timetable SET time = ?  WHERE time = ?";
    	
    	 try {
             PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query);
             
             preparedStatement.setString(1, depHour);
             preparedStatement.setString(2, currHour);
                         
             return (preparedStatement.executeUpdate()>0);
         }catch (SQLException ex){
             ex.printStackTrace();
             return false;
         }
    	
   }
    
    
    public static boolean deleteHour(String currHour) {
    	String query = "DELETE FROM timetable WHERE time = ?";
    	
    	 try {
             PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query);
             
             preparedStatement.setString(1, currHour);
                         
             return (preparedStatement.executeUpdate()>0);
         }catch (SQLException ex){
             ex.printStackTrace();
             return false;
         }
    	
   }
    
    
    
    
    
    public static List<Timetable> getRoutes(){
    	
        List<Timetable> routeList=new ArrayList<>();

        String query= "SELECT routes.id,timetable.id,companies.name,city1.name,city2.name,timetable.time, routes.schedule_id \n" + 
        		"FROM routes JOIN companies JOIN timetable JOIN cities as city1 JOIN cities as city2 \n " + 
        		"where routes.company_id = companies.id and routes.schedule_id=timetable.id and \n" + 
        		" start_city=city1.id and end_city=city2.id;"; 
        		
        try {
            PreparedStatement preparedStatement=DBConnection.getConnection().prepareStatement(query);
            ResultSet resultSet=preparedStatement.executeQuery();

            while (resultSet.next()){
                Timetable route=new Timetable(resultSet.getInt(1),resultSet.getInt(2),resultSet.getString(3),
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
    
    
    public static List<Timetable> getSchedule(){
    	
        List<Timetable> scheduleList=new ArrayList<>();

        String query= "SELECT time FROM timetable";
        try {
            PreparedStatement preparedStatement=DBConnection.getConnection().prepareStatement(query);
            ResultSet resultSet=preparedStatement.executeQuery();

            while (resultSet.next()){
                Timetable schedule=new Timetable(resultSet.getString(1));
                scheduleList.add(schedule);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }

        return scheduleList;
    }

    public static void showSchedule(TableView tv) {
        List<Timetable> schedules = Timetable.getSchedule();

        ObservableList<Timetable> schedulesList = FXCollections.observableArrayList();

        for(int i = 0; i < schedules.size(); i++) {
        	schedulesList.add(schedules.get(i));
        }

        tv.setItems(schedulesList);
    }
    
    
    // Funksioni i Visit
    public static int getIdByName(String name){
        String query="SELECT id FROM timetable WHERE time=?";
        try {
            PreparedStatement preparedStatement=DBConnection.getConnection().prepareStatement(query);
            preparedStatement.setString(1,name);
            ResultSet resultSet=preparedStatement.executeQuery();
            resultSet.beforeFirst();
            resultSet.next();
            return resultSet.getInt(1);
        }catch (SQLException ex){
            ex.printStackTrace();
            return 2;
        }
    }

    public static void showSchedulesOnComboBox(ComboBox<String> cbo) {
        String query = "SELECT time FROM timetable";
        try {
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cbo.getItems().add(resultSet.getString(1));
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    

	



}
