
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.print.Book;

class RegularUI extends Pane {
    private ComboBox<String> cboStartCity = new ComboBox<>();
    private ComboBox<String> cboEndCity = new ComboBox<>();

    private Button btnSearch = new Button("Search");
    private TableView tabela = new TableView();

    BorderPane borderPane = new BorderPane();

    GridPane gridPane = new GridPane();



    public RegularUI(){

        cboStartCity.getItems().addAll("Prishtine","Peje", "Prizren");
        cboStartCity.setValue("Select starting point");


        cboEndCity.getItems().addAll("Prishtine","Peje", "Prizren");
        cboEndCity.setValue("Select ending point");

        HBox hBox = new HBox(10);


        hBox.getChildren().addAll(cboStartCity, cboEndCity, btnSearch);
        hBox.setPadding(new Insets(0,10,10,40));

        TableColumn<String, Book> col1 = new TableColumn<>("Vendi i Nisjes");
        col1.setCellValueFactory(new PropertyValueFactory<>("start"));
        col1.setPrefWidth(100);

        TableColumn<String, Book> col2 = new TableColumn<>("Vendi i Arritjes");
        col2.setCellValueFactory(new PropertyValueFactory<>("end"));
        col2.setPrefWidth(100);

        TableColumn<String, Book> col3 = new TableColumn<>("Ora e Nisjes");
        col3.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        col3.setPrefWidth(100);

        TableColumn<String, Book> col4 = new TableColumn<>("Kompania");
        col4.setCellValueFactory(new PropertyValueFactory<>("company_id"));
        col4.setPrefWidth(100);

        TableColumn<String, Book> col5 = new TableColumn<>("Cmimi");
        col5.setCellValueFactory(new PropertyValueFactory<>("price"));
        col5.setPrefWidth(50);


        tabela.getColumns().addAll(col1, col2, col3, col4, col5);

        borderPane.setTop(hBox);
        borderPane.setCenter(tabela);
        borderPane.setPadding(new Insets(10, 0, 10, 0));

        borderPane.setPrefWidth(450);
        getChildren().add(borderPane);


        cboEndCity.setOnAction(event -> System.out.println("City Seleced"+ cboEndCity.getValue()));
    }

}



