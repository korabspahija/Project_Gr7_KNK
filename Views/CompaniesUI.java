package Views;

import Helpers.Help;
import Main.Test;
import Models.*;
import Helpers.DBConnection;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Time;

public class CompaniesUI  extends VBox{

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

    public CompaniesUI(int userId,Stage curentStage){

        Menu generalMenu = new Menu("File");

        MenuItem exitMenuItem = new MenuItem("Exit");
        MenuItem backMenuItem = new MenuItem("Back");
        MenuItem logoutMenuItem=new MenuItem("LogOut");

        generalMenu.getItems().addAll(backMenuItem,exitMenuItem,logoutMenuItem);

        Menu helpMenu = new Menu("Help") ;
        MenuItem aboutHelpItem = new MenuItem("About");
        helpMenu.getItems().add(aboutHelpItem);

        Menu languagesMenu = new Menu("Languages");
        MenuItem english = new MenuItem("English");
        MenuItem albanian = new MenuItem("Albanian");
        languagesMenu.getItems().addAll(english, albanian);

        MenuBar mb = new MenuBar();
        mb.getMenus().addAll(generalMenu, helpMenu,languagesMenu);

        aboutHelpItem.setOnAction(event -> {
            Help.about();
        });

        logoutMenuItem.setOnAction(event -> {
            Test test=new Test();
            test.start(new Stage());
            curentStage.hide();
        });
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




        TableColumn<String, CompanyRoutes> col1 = new TableColumn<>("Start City");
        col1.setCellValueFactory(new PropertyValueFactory<>("startCity"));
        col1.setPrefWidth(100);

        TableColumn<String,  CompanyRoutes> col2 = new TableColumn<>("End City");
        col2.setCellValueFactory(new PropertyValueFactory<>("endCity"));
        col2.setPrefWidth(100);

        TableColumn<String,  CompanyRoutes> col3 = new TableColumn<>("Schedule");
        col3.setCellValueFactory(new PropertyValueFactory<>("schedule"));
        col3.setPrefWidth(100);

        TableColumn<String,  CompanyRoutes> col4 = new TableColumn<>("Company");
        col4.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        col4.setPrefWidth(100);

        TableColumn<String,  CompanyRoutes> col5 = new TableColumn<>("Price");
        col5.setCellValueFactory(new PropertyValueFactory<>("price"));
        col5.setPrefWidth(50);


        tabela.getColumns().addAll(col1, col2, col3, col4, col5);


        CompanyRoutes.showRoutes(tabela,userId);
        btnSearch.setOnAction(event -> {
            CompanyRoutes.showRoutesCompany(tabela,cboStart.getValue(),cboEnd.getValue(), userId);
        });

        btnAdd.setOnAction(event ->
        {
            CompanyRoutes.insertRoute(Double.parseDouble(tfPrice.getText()), cboStart.getValue(), cboEnd.getValue(), cboTime.getValue(),Companies.getCompanyId(userId));
            CompanyRoutes.showRoutes(tabela,userId);

        });


        btnDelete.setOnAction(event ->{

            CompanyRoutes.deleteRoute(Integer.parseInt(tfID.getText()));
            CompanyRoutes.showRoutes(tabela,userId);

        });

        btnUpdate.setOnAction(event -> {
            CompanyRoutes.updateRoute(Double.parseDouble(tfPrice.getText()),cboStart.getValue(),cboEnd.getValue(),cboTime.getValue(),Integer.parseInt(tfID.getText()));
            CompanyRoutes.showRoutes(tabela,userId);

        });
        HBox mainpane=new HBox();
        mainpane.getChildren().addAll(leftPane,tabela);

        getChildren().addAll(mb,mainpane);

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
