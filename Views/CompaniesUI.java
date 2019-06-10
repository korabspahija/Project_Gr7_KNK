package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.awt.print.Book;


public class CompaniesUI  extends HBox{

    private TextField txtID = new TextField();
    private TextField txtStart  = new TextField();;
    private TextField txtEnd  = new TextField();

    private Button btnSearch = new Button("Search");
    private Button btnAdd = new Button("Add");
    private Button btnUpdate = new Button("Update");
    private Button btnDelete = new Button("Delete");
    private TableView tabela = new TableView();

    private VBox leftPane = new VBox();
    private GridPane formPane = new GridPane();
    private HBox hBox = new HBox();

    CompaniesUI(){
        formPane.addRow(0,new Label("ID :"), txtID);
        formPane.addRow(1,new Label("Start :"), txtStart);
        formPane.addRow(2,new Label("End :"), txtEnd);

        hBox.getChildren().addAll(btnSearch,btnAdd,btnUpdate);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(10,10,10,10));
        hBox.setSpacing(10);

        leftPane.getChildren().addAll(formPane,hBox);
        leftPane.setAlignment(Pos.CENTER);
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

        TableColumn<Button,Book> col6 = new TableColumn<>("Delete");
        col6.setCellValueFactory(new PropertyValueFactory<>("btnDelete"));
        col6.setPrefWidth(50);
        tabela.getColumns().addAll(col1, col2, col3, col4, col5,col6);


        getChildren().addAll(leftPane,tabela);
    }
}
