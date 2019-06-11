

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class AdminView extends BorderPane {
	
	private Button btnUsers = new Button("Modifikim i user-eve");
    private Button btnCities = new Button ("Modifikimi i qyteteve");
    private Button btnLines = new Button("Modifikim i linjave");
    private Button btnSchedule= new Button("Modifikim i orarit");
	
	public AdminView() {
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
	    
	    HBox hbox = new HBox(10);
	    TextField field = new TextField();
	    HBox.setHgrow(field, Priority.ALWAYS);
	    Button btnBack = new Button("Back");
	    hbox.getChildren().addAll(btnBack,new Label("Search:"), field, new Button("Go"));
	     
	    vBox.getChildren().addAll(btnUsers,btnCities,btnLines,btnSchedule);
	    setLeft(vBox);
	    setTop(hbox);	    
	}

}
