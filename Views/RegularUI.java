package Views;

import Models.*;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class RegularUI extends Pane {
    private ComboBox<String> cboStartCity = new ComboBox<>();
    private ComboBox<String> cboEndCity = new ComboBox<>();

    private Button btnSearch = new Button("Search");
    private TableView tabela = new TableView();

    BorderPane borderPane = new BorderPane();

    GridPane gridPane = new GridPane();



    public RegularUI(){

        Cities.showCiticesOnComboBox(cboStartCity);
        cboStartCity.setValue("Select starting point");

        Cities.showCiticesOnComboBox(cboEndCity);
        cboEndCity.setValue("Select ending point");

        HBox hBox = new HBox(10);


        hBox.getChildren().addAll(cboStartCity, cboEndCity, btnSearch);
        hBox.setPadding(new Insets(0,10,10,40));

        TableColumn<String, CompanyRoutes> col1 = new TableColumn<>("Departure City");
        col1.setCellValueFactory(new PropertyValueFactory<>("startCity"));
        col1.setPrefWidth(100);

        TableColumn<String,  CompanyRoutes> col2 = new TableColumn<>("Arrival City");
        col2.setCellValueFactory(new PropertyValueFactory<>("endCity"));
        col2.setPrefWidth(100);

        TableColumn<String,  CompanyRoutes> col3 = new TableColumn<>("Starting Time");
        col3.setCellValueFactory(new PropertyValueFactory<>("schedule"));
        col3.setPrefWidth(100);

        TableColumn<String,  CompanyRoutes> col4 = new TableColumn<>("Company Name");
        col4.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        col4.setPrefWidth(100);

        TableColumn<String,  CompanyRoutes> col5 = new TableColumn<>("Price");
        col5.setCellValueFactory(new PropertyValueFactory<>("price"));
        col5.setPrefWidth(50);


        tabela.getColumns().addAll(col1, col2, col3, col4, col5);

        borderPane.setTop(hBox);
        borderPane.setCenter(tabela);
        borderPane.setPadding(new Insets(10, 0, 10, 0));

        borderPane.setPrefWidth(450);
        getChildren().add(borderPane);


        btnSearch.setOnAction(e -> CompanyRoutes.showRoutes(tabela,cboStartCity.getValue(),cboEndCity.getValue()));
    }
}



