package sample;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.print.Book;

class RegularUI extends Pane {
    private TextField txtStart  = new TextField();;
    private TextField txtEnd  = new TextField();
    private Button btnSearch = new Button("Search");
    private TableView tabela = new TableView();

    BorderPane borderPane = new BorderPane();

    GridPane gridPane = new GridPane();



    RegularUI(){
        gridPane.addRow(0, new Label("Start : "), txtStart, new Label("End : "), txtEnd, btnSearch);

        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(0, 5, 10, 5));

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

        borderPane.setTop(gridPane);
        borderPane.setCenter(tabela);
        borderPane.setPadding(new Insets(10, 0, 10, 0));

        getChildren().add(borderPane);
    }




    }



