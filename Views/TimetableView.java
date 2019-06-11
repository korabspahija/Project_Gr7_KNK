

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class TimetableView extends Pane{
	
	private TableView table;
	
	
	public TimetableView() {
	
	// Layout osht ni Hbox i madh qe e permban ni VBox edhe ni Tabele, VBox permban GridPane	
		
	HBox hbox = new HBox();
	// Forma per Adminin
	VBox vbox = new VBox();
	vbox.setPadding(new Insets(30, 30, 0, 10));
	
	// Gridi
	GridPane grid = new GridPane();
		
	grid.add(new Label("Departuasdre Time: "), 0 ,0);
	
	TextField txtDepTime = new TextField();
	grid.add(txtDepTime, 1, 1);
	
	Button btnChangeTime = new Button("CHANGE");
	GridPane.setHalignment(btnChangeTime, HPos.RIGHT);
	
	grid.setVgap(10);
	
	grid.add(btnChangeTime, 0, 2);
	
	vbox.getChildren().add(grid);
		
		
	// Tabela	
	// Kompania	
	TableColumn colCompany = new TableColumn("Company");
	colCompany.setCellValueFactory(new PropertyValueFactory<>("company_id"));
	colCompany.setPrefWidth(100);
	// Vendi i nisjes
	TableColumn colStartCity = new TableColumn("Departure");
	colStartCity.setCellValueFactory(new PropertyValueFactory<>("start_city"));
	colStartCity.setPrefWidth(100);
	// Vendi i arritjes
	TableColumn colDestCity = new TableColumn("Destination");
	colDestCity.setCellValueFactory(new PropertyValueFactory<>("end_city"));
	colDestCity.setPrefWidth(100);
	// Ora e nisjes
	TableColumn colStartHour = new TableColumn("Departure Time");
	colStartHour.setCellValueFactory(new PropertyValueFactory<>("schedule"));
	colStartHour.setPrefWidth(100);
	// Ora e arritjes se nuk ka atribut arrival time n'db (ata qe e kan bo databazen jane super bala)
	//	TableColumn colArrivalHour = new TableColumn("Arrival Time");
	//	colArrivalHour.setCellValueFactory(new PropertyValueFactory<>("company_id"));
	//	colArrivalHour.setPrefWidth(100);
	
	table = new TableView();
	
	table.getColumns().addAll(colCompany, colStartCity, colDestCity, colStartHour);	
	
	hbox.getChildren().addAll(vbox, table);
	
	getChildren().add(hbox);
	}
	
	
}

