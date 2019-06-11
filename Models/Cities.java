package Models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Helpers.DBConnection;
public class Cities {
	
	
	private int id;
	private String name;
	 
	
	public Cities(int id, String name){
		this.id=id;
		this.name=name;
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public static boolean addCity(String name) {
		String query="INSERT INTO `sistemiautobuseve`.`cities` (`name`) VALUES (?);";
		   try {
	            PreparedStatement preparedStatement= DBConnection.getConnection().prepareStatement(query);
	            preparedStatement.setString(1,name);

	            return(preparedStatement.executeUpdate()>0);
	        }catch (SQLException ex){
	            ex.printStackTrace();
	            return false;
	        }
	}
	public static boolean removeCity(String name) {
		
		String query="DELETE FROM cities WHERE name=`?` ";
		   try {
	            PreparedStatement preparedStatement= DBConnection.getConnection().prepareStatement(query);
	            preparedStatement.setString(1,name);

	            return(preparedStatement.executeUpdate()>0);
	        }catch (SQLException ex){
	            ex.printStackTrace();
	            return false;
	        }
	}
	 public static List<Cities> getCities(){
	        List<Cities> citiesList=new ArrayList<>();

	        String query="SELECT * FROM cities";

	        try {
	            PreparedStatement preparedStatement=DBConnection.getConnection().prepareStatement(query);
	            ResultSet resultSet=preparedStatement.executeQuery();

	            while (resultSet.next()){
	                Cities cities=new Cities(resultSet.getInt(1),resultSet.getString(2));
	                citiesList.add(cities);
	            }
	        }catch (SQLException ex){
	            ex.printStackTrace();
	        }

	        return citiesList;
	    }
	
}
