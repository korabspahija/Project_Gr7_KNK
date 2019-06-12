package Views;

import Models.CompanyRoutes;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import Models.Cities;

public class CompaniesUI  extends HBox{

    private TextField txtID = new TextField();
    private TextField txtTime  = new TextField();
    private TextField txtPrice = new TextField();
    private ComboBox<String> cboStart = new ComboBox<>();
    private ComboBox<String> cboEnd = new ComboBox<>();

    private Button btnSearch = new Button("Search");
    private Button btnAdd = new Button("Add");
    private Button btnUpdate = new Button("Update");
    private Button btnDelete = new Button("Delete");

    private TableView tabela = new TableView();

    private VBox leftPane = new VBox();
    private GridPane formPane = new GridPane();
    private HBox hBox = new HBox();

    public CompaniesUI(){


        txtID.setDisable(true);
        cboStart.setValue("Starting point");
        cboEnd.setValue("Ending point");

        // caktimi i width
        txtID.setMaxWidth(150);
        txtPrice.setMaxWidth(150);
        txtTime.setMaxWidth(150);
        cboStart.setMaxWidth(150);
        cboEnd.setMaxWidth(150);

        Cities.showCiticesOnComboBox(cboStart);
        Cities.showCiticesOnComboBox(cboEnd);

        // krijimi i formes ??
        formPane.addRow(0,new Label("ID :"), txtID);
        formPane.addRow(1,new Label("Start :"), cboStart);
        formPane.addRow(2,new Label("End :"), cboEnd);
        formPane.addRow(3,new Label("Time :"), txtTime);
        formPane.addRow(4,new Label("Price :"), txtPrice);
        formPane.setPadding(new Insets(0,0,0,10));
        formPane.setVgap(10);
        formPane.setHgap(10);


        // i butonave

        hBox.getChildren().addAll(btnSearch,btnAdd,btnUpdate,btnDelete);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(10,10,10,10));
        hBox.setSpacing(7.5);

        // i krejt panes n'tmajt
        leftPane.getChildren().addAll(formPane,hBox);
        leftPane.setPadding(new Insets(10));




        TableColumn<String, CompanyRoutes> col1 = new TableColumn<>("Vendi i Nisjes");
        col1.setCellValueFactory(new PropertyValueFactory<>("startCity"));
        col1.setPrefWidth(100);

        TableColumn<String,  CompanyRoutes> col2 = new TableColumn<>("Vendi i Arritjes");
        col2.setCellValueFactory(new PropertyValueFactory<>("endCity"));
        col2.setPrefWidth(100);

        TableColumn<String,  CompanyRoutes> col3 = new TableColumn<>("Ora e Nisjes");
        col3.setCellValueFactory(new PropertyValueFactory<>("schedule"));
        col3.setPrefWidth(100);

        TableColumn<String,  CompanyRoutes> col4 = new TableColumn<>("Kompania");
        col4.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        col4.setPrefWidth(100);

        TableColumn<String,  CompanyRoutes> col5 = new TableColumn<>("Cmimi");
        col5.setCellValueFactory(new PropertyValueFactory<>("price"));
        col5.setPrefWidth(50);


        tabela.getColumns().addAll(col1, col2, col3, col4, col5);


        getChildren().addAll(leftPane,tabela);
    }
}
