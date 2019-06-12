package per_projekt;

import per_projekt.Timetable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class TimetableView extends Pane{
	
	private TableView tblTimetable = new TableView();
	private TableView tblDepTime = new TableView();
	TextField txtDepTime = new TextField();
	private int routeId;
	private int schedId;
	
	public TimetableView() {
	
	// Layout osht ni Hbox i madh qe e permban ni VBox edhe ni Tabele, VBox permban GridPane	
		
	HBox hbox = new HBox();
	// Forma per Adminin
	VBox vbox = new VBox();
	vbox.setPadding(new Insets(30, 30, 0, 10));
	
	// Gridi
	GridPane grid = new GridPane();
		
	grid.add(new Label("Departure Time: "), 0 ,0);
	
	
	grid.add(txtDepTime, 1, 0);
	
	
	Button btnChangeTime = new Button("CHANGE");
	GridPane.setHalignment(btnChangeTime, HPos.RIGHT);
	btnChangeTime.setOnAction(e->{
		changeStartHour();
	});
	
	grid.setVgap(10);
	
	grid.add(btnChangeTime, 1, 2);
	
	vbox.getChildren().add(grid);
		
		
	// Tabela	
	// Kompania	
	TableColumn<String, Timetable> colCompany = new TableColumn<>("Company");
	colCompany.setCellValueFactory(new PropertyValueFactory<>("id"));
	colCompany.setPrefWidth(100);
	// Vendi i nisjes
	TableColumn<String, Timetable> colStartCity = new TableColumn<>("Departure");
	colStartCity.setCellValueFactory(new PropertyValueFactory<>("startCity"));
	colStartCity.setPrefWidth(100);
	// Vendi i arritjes
	TableColumn<String, Timetable> colDestCity = new TableColumn<>("Destination");
	colDestCity.setCellValueFactory(new PropertyValueFactory<>("endCity"));
	colDestCity.setPrefWidth(100);
	// Ora e nisjes
	TableColumn<String, Timetable> colStartHour = new TableColumn<>("Departure Hours");
	colStartHour.setCellValueFactory(new PropertyValueFactory<>("startHour"));
	colStartHour.setPrefWidth(100);
	// Ora e arritjes se nuk ka atribut arrival time n'db (ata qe e kan bo databazen jane super bala)
	//	TableColumn colArrivalHour = new TableColumn("Arrival Time");
	//	colArrivalHour.setCellValueFactory(new PropertyValueFactory<>("company_id"));
	//	colArrivalHour.setPrefWidth(100);
	
	
	tblTimetable.setRowFactory(tv -> {
        
		TableRow<Timetable> row = new TableRow<>();
        
        row.setOnMouseClicked(event -> {
        txtDepTime.setText(String.valueOf(row.getItem().getStartHour()));
        routeId = row.getItem().getId();
                   
        });
        
        return row ;
    });
	
	tblTimetable.getColumns().addAll(colCompany, colStartCity, colDestCity, colStartHour);	
	
	// Tabela per ndryshim te kohes
	
	TableColumn<String, Timetable> colSchedule = new TableColumn<>("Deparutre Hours");
	colSchedule.setCellValueFactory(new PropertyValueFactory<>("schid"));
	colSchedule.setPrefWidth(250);
	
	
	tblDepTime.getColumns().addAll(colSchedule);
	
	hbox.setSpacing(17);
	hbox.getChildren().addAll(vbox, tblTimetable, tblDepTime);
	
	getChildren().add(hbox);
	
	Timetable.showRoutes(tblTimetable);
	Timetable.showSchedule(tblDepTime);
	}

	// titleTxt.getText()
	private void changeStartHour() {
		if (Timetable.updateRoute(txtDepTime.getText(), routeId)) {
			System.out.println(txtDepTime.getText());
			Timetable.showRoutes(tblTimetable);
			clearForm();
		}
		
	}
	
	private void clearForm() {
		txtDepTime.setText("");
	}
	
	
	
}

