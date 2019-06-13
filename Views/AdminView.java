package Views;

import Helpers.Help;
import Main.Test;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class AdminView extends BorderPane {
	
	private Button btnUsers = new Button("Modify Users");
    private Button btnCities = new Button ("Modify Cities");
    private Button btnLines = new Button("Modify Routes");
    private Button btnSchedule= new Button("Modify Schedule");
	
	public AdminView(Stage curentStage) {

		// General Menu
        Menu generalMenu = new Menu("File"); 
        
        MenuItem exitMenuItem = new MenuItem("Exit"); 
        
        MenuItem backMenuItem = new MenuItem("Back");

        MenuItem logoutMenuItem=new MenuItem("LogOut");
        
        exitMenuItem.setOnAction(e -> {
        	Platform.exit();
        });


        logoutMenuItem.setOnAction(e->{
            Test test=new Test();
            test.start(new Stage());
            curentStage.hide();
        });
        
        generalMenu.getItems().addAll(backMenuItem,exitMenuItem,logoutMenuItem);

        
        // HelpMenu
        Menu helpMenu = new Menu("Help") ;
       
        MenuItem aboutHelpItem = new MenuItem("About"); 
        helpMenu.getItems().add(aboutHelpItem); 
        
        Menu languagesMenu = new Menu("Languages");
        
        MenuItem english = new MenuItem("English");
        
        MenuItem albanian = new MenuItem("Albanian"); 
        languagesMenu.getItems().addAll(english, albanian);

        
        aboutHelpItem.setOnAction(e -> {
        	Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("About us");
            alert.setHeaderText("We hope you are satisfied with our programm");
            alert.setContentText("We worked very hard and i hope u like our program");
            alert.showAndWait();
        });

        aboutHelpItem.setOnAction(e->{
            Help.about();
        });
  
        MenuBar mb = new MenuBar(); 
  
        mb.getMenus().addAll(generalMenu, helpMenu,languagesMenu); 
  
        VBox vbMenu = new VBox(mb);
		
		VBox vBox = new VBox(15);
		
	    vBox.setPadding(new Insets(15, 15, 15, 15));
	    
	    btnUsers.setPrefHeight(140);
	    btnUsers.setPrefWidth(300); 
	    
	    btnCities.setPrefHeight(140);
	    btnCities.setPrefWidth(300);
	    
	    btnLines.setPrefHeight(140);
	    btnLines.setPrefWidth(300);
	    
	    btnSchedule.setPrefHeight(140);
	    btnSchedule.setPrefWidth(300);
	    
	    TextField field = new TextField();
	    HBox.setHgrow(field, Priority.ALWAYS);
	    
	     
	    vBox.getChildren().addAll(btnUsers,btnCities,btnLines,btnSchedule);
	    setLeft(vBox);
	    setTop(vbMenu);	
	    
	    
	    
	    backMenuItem.setOnAction(e->{
	    	setRight(new HBox());
	    });
	    
	    
	    btnCities.setOnAction(e-> {
            setRight(new CityView());
        });


	    btnLines.setOnAction(e->{
	        setRight(new RoutesView());
        });

	    btnUsers.setOnAction(event -> {
	        setRight(new CompaniesView());
        });

	    btnSchedule.setOnAction(event -> {
	        setRight(new TimetableView());
        });
	}
  
}
