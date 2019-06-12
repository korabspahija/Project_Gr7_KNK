import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.List;
import Models.Cities;

public class CityView extends VBox{
//
  // public void start(Stage stage)
   public CityView()
    {
	 
        

        ComboBox<String> cboCity = new ComboBox<String>();
        cboCity.setMaxWidth(Double.MAX_VALUE);
       
        HBox hBoxRemoveCity = new HBox();
        
        HBox hBoxAddCity = new HBox();
        
        Button btnRemoveCity = new Button("Remove City");
        
        VBox MainVBox = new VBox(45);
        
        TextField tfAddCity = new TextField();
        
        
        Button btnAddCity = new Button("Add City");
        
        TableView table = new TableView();
        
        btnAddCity.setOnAction(e->{
        	if (!tfAddCity.getText().isEmpty()) {
        	Cities.addCity(tfAddCity.getText());
        	List<Cities> cities= Cities.getCities();
        	
            ObservableList<Cities> citylist = FXCollections.observableArrayList();
            
            for(int i = 0; i < cities.size(); i++) {
            	citylist.add(cities.get(i));
            }
            
            table.setItems(citylist);}

          else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("");
                alert.setHeaderText(null);
                alert.setContentText("Plase fill this field");
                alert.showAndWait();}
        	
        });
        
        //mock table kur krijohet lidhet me databaze krijohet e verteta 
        btnRemoveCity.setOnAction(e->{
        	String output = cboCity.getSelectionModel().getSelectedItem().toString();
        	Cities.removeCity(output); 
        List<Cities> cities= Cities.getCities();
    	
        ObservableList<Cities> citylist = FXCollections.observableArrayList();
        
        for(int i = 0; i < cities.size(); i++) {
        	citylist.add(cities.get(i));
        	}
            table.setItems(citylist);
        });
        
        List<Cities> cities= Cities.getCities();
    	
        ObservableList<Cities> citylist = FXCollections.observableArrayList();
        
        for(int i = 0; i < cities.size(); i++) {
        	citylist.add(cities.get(i));
        }
        
        table.setItems(citylist);
        table.setEditable(true);
      

        TableColumn<String, Cities> col1 = new TableColumn<>("City ID");
        col1.setCellValueFactory(new PropertyValueFactory<>("id"));
        col1.setPrefWidth(265);

        TableColumn<String, Cities> col2 = new TableColumn<>("City Name");
        col2.setCellValueFactory(new PropertyValueFactory<>("name"));
        col2.setPrefWidth(265);

        table.getColumns().addAll(col1, col2);
//
        table.setEditable(true);


        
        
        hBoxRemoveCity.getChildren().addAll(cboCity,btnRemoveCity);
        
        hBoxAddCity.getChildren().addAll(tfAddCity,btnAddCity);
        
        
        MainVBox.getChildren().addAll(new Label("City management"),hBoxAddCity,hBoxRemoveCity,table);
        

        //Scene scene = new Scene(MainVBox,530,500);
//        stage.setScene(scene);
//        stage.show();

        cboCity.setMinWidth(200);
        cboCity.setPrefWidth(cboCity.getWidth());

        Cities.showCiticesOnComboBox(cboCity);
        getChildren().add(MainVBox);

    }

//    public static void main(String[] args)
//    {
//        launch();
//    }
}