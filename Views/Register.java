package per_projekt;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;

public class Register extends Pane{
				
		VBox vbox = new VBox();
		public Register(){
		Text txt = new Text("Insert your information!");
		txt.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
		txt.setFill(Color.DARKBLUE);
		vbox.setMargin(txt, new Insets(0,0,0, 50));
		// GridPane per textfield-at edhe labels
		
		GridPane grid = new GridPane();
		grid.setHgap(5);
		grid.setVgap(5);
		grid.setPadding(new Insets(20,20,20,50));
		// Row1
		grid.add(new Label("Name of the Company: "), 0, 0);
		TextField txtComName = new TextField();
		grid.add(txtComName, 1, 0);
		//Row2
		grid.add(new Label("Ashiq naj sen: "), 0, 1);
		TextField txt2 = new TextField();
		grid.add(txt2, 1, 1);
		//Row3
		grid.add(new Label("Email: "), 0, 2);
		TextField txtEmail = new TextField();
		grid.add(txtEmail, 1, 2);
		//Row4
		grid.add(new Label("Ashiq naj sen: "), 0, 3);
		TextField txt3 = new TextField();
		grid.add(txt3, 1, 3);
		//Row5
		grid.add(new Label("Admin's permission key: "), 0, 4);
		TextField admKey = new TextField();
		grid.add(admKey, 1, 4);
		
		// Buttoni Register
		Button btnRegister = new Button("Register");
		btnRegister.setTranslateX(88);
		btnRegister.setTranslateY(6);
		grid.add(btnRegister, 1,5);
		
		vbox.getChildren().addAll(txt, grid);

		getChildren().add(vbox);
		}
}