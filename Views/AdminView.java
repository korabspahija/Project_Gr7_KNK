import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class AdminView extends BorderPane {
	
	private Button btnUsers = new Button("Modify Users");
    private Button btnCities = new Button ("Modify Cities");
    private Button btnLines = new Button("Modify Routes");
    private Button btnSchedule= new Button("Modify Schedule");
	
	public AdminView() {
		
		// General Menu
        Menu generalMenu = new Menu("File"); 
        
        MenuItem exitMenuItem = new MenuItem("Exit"); 
        
        MenuItem backMenuItem = new MenuItem("Back"); 
        
        exitMenuItem.setOnAction(e -> {
        	Platform.exit();
        });
        
        generalMenu.getItems().addAll(backMenuItem,exitMenuItem); 

        
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
	    
	    
	    btnCities.setOnAction(e->{
	    setRight(new CityView());});
	   
	}
  
}
