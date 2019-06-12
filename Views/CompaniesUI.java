package Views;

import Models.*;
import Helpers.DBConnection;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.Time;

public class CompaniesUI  extends HBox{

    private TextField tfID = new TextField();
    private TextField tfPrice = new TextField();
    private ComboBox<String> cboTime = new ComboBox<>();
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

    public CompaniesUI(int userId){


        tfID.setDisable(true);

        cboStart.setValue("Starting point");
        cboEnd.setValue("Ending point");
        cboTime.setValue("Schedule");

        // caktimi i width
        tfID.setMaxWidth(150);
        tfPrice.setMaxWidth(150);
        cboTime.setMaxWidth(150);
        cboStart.setMaxWidth(150);
        cboEnd.setMaxWidth(150);

        Cities.showCiticesOnComboBox(cboStart);
        Cities.showCiticesOnComboBox(cboEnd);
        Timetable.showSchedulesOnComboBox(cboTime);

        // krijimi i formes ??
        formPane.addRow(0,new Label("ID :"), tfID);
        formPane.addRow(1,new Label("Start :"), cboStart);
        formPane.addRow(2,new Label("End :"), cboEnd);
        formPane.addRow(3,new Label("Time :"), cboTime);
        formPane.addRow(4,new Label("Price :"), tfPrice);
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
        btnSearch.setOnAction(event -> {
            CompanyRoutes.showRoutesCompany(tabela,cboStart.getValue(),cboEnd.getValue(), userId);
        });
        btnAdd.setOnAction(event ->
        {
            CompanyRoutes.insertRoute(Double.parseDouble(tfPrice.getText()), cboStart.getValue(), cboEnd.getValue(), cboTime.getValue());
            CompanyRoutes.showRoutesCompany(tabela,cboStart.getValue(),cboEnd.getValue(), userId);

        });


        btnDelete.setOnAction(event ->{

            CompanyRoutes.deleteRoute(Integer.parseInt(tfID.getText()));
            CompanyRoutes.showRoutesCompany(tabela,cboStart.getValue(),cboEnd.getValue(), userId);

        });

        btnUpdate.setOnAction(event -> {
            CompanyRoutes.updateRoute(Double.parseDouble(tfPrice.getText()),cboStart.getValue(),cboEnd.getValue(),cboTime.getValue(),Integer.parseInt(tfID.getText()));
            CompanyRoutes.showRoutesCompany(tabela,cboStart.getValue(),cboEnd.getValue(),userId);

        });
        getChildren().addAll(leftPane,tabela);

        tabela.setRowFactory(tv -> {

            TableRow<CompanyRoutes> row = new TableRow<>();

            row.setOnMouseClicked(event -> {
                tfID.setText(String.valueOf(row.getItem().getId()));
                tfPrice.setText(String.valueOf( row.getItem().getPrice()));
                cboTime.setValue(String.valueOf(row.getItem().getSchedule()));
                cboEnd.setValue(String.valueOf(row.getItem().getEndCity()));
                cboStart.setValue(String.valueOf(row.getItem().getStartCity()));
            });

            return row ;
        });
    }
}
