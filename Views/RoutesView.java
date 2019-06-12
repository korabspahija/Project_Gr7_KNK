package Views;

import Models.Cities;
import Models.Route;
import Models.Timetable;
import javafx.geometry.Insets;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.control.*;

import java.sql.Time;


public class RoutesView extends HBox{

    private TextField tfId=new TextField();
    private TextField tfCompany=new TextField();
    private TextField tfPrice=new TextField();

    private  ComboBox<String> cboStartCity = new ComboBox<>();
    private  ComboBox<String> cboEndCity=new ComboBox<>();
    private ComboBox<String>cboSchedule=new ComboBox<>();

    private Button btnInsert=new Button("Insert");
    private Button btnUpdate=new Button("Update");
    private Button btnDelete=new Button("Delete");
    private Button btnClear=new Button("Clear");

    private TableView routesTable = new TableView();


    public RoutesView (){
        GridPane formPane=new GridPane();
        formPane.setHgap(10);
        formPane.setVgap(10);

        Cities.showCiticesOnComboBox(cboStartCity);
        cboStartCity.setValue("Startin Point");

        Cities.showCiticesOnComboBox(cboEndCity);
        cboEndCity.setValue("End Point");

        Timetable.showSchedulesOnComboBox(cboSchedule);
        cboSchedule.setValue("Item 1");



        formPane.addRow(0,new Label("ID"),tfId);
        formPane.addRow(1,new Label("Company"),tfCompany);
        formPane.addRow(2,new Label("Start City"),cboStartCity);
        formPane.addRow(3,new Label("End City"),cboEndCity);
        formPane.addRow(4,new Label("Schedule"),cboSchedule);
        formPane.addRow(5,new Label("Price"),tfPrice);

        tfId.setDisable(true);

        HBox buttonsPane=new HBox(10);
        buttonsPane.getChildren().addAll(btnInsert,btnUpdate,btnClear,btnDelete);

        VBox leftPane=new VBox(10);
        leftPane.getChildren().addAll(formPane,buttonsPane);

        TableColumn<String, Route> column1 = new TableColumn<>("ID");
        column1.setCellValueFactory(new PropertyValueFactory("id"));
        column1.setPrefWidth(70);

        TableColumn<String, Route>column2 = new TableColumn<>("Company");
        column2.setCellValueFactory(new PropertyValueFactory("companyName"));
        column2.setPrefWidth(110);

        TableColumn<String, Route> column3 = new TableColumn<>("Start City");
        column3.setCellValueFactory(new PropertyValueFactory("startCity"));
        column3.setPrefWidth(120);

        TableColumn<String, Route> column4 = new TableColumn<>("End City");
        column4.setCellValueFactory(new PropertyValueFactory("endCity"));
        column4.setPrefWidth(120);

        TableColumn<String, Route> column5 = new TableColumn<>("Schedule");
        column5.setCellValueFactory(new PropertyValueFactory("schedule"));
        column5.setPrefWidth(90);

        TableColumn<String, Route> column6=new TableColumn<>("Price");
        column6.setCellValueFactory(new PropertyValueFactory("price"));
        column6.setPrefWidth(60);

        routesTable.getColumns().addAll(column1,column2,column3,column4,column5,column6);
        routesTable.setPrefHeight(200);
        routesTable.setPrefWidth(650);

        getChildren().addAll(leftPane,routesTable);
        setPadding(new Insets(15));

        Route.showRoutes(routesTable);

        routesTable.setRowFactory(tv->{
            TableRow<Route> row=new TableRow<>();

            row.setOnMouseClicked(event -> {
                tfId.setText(String.valueOf(row.getItem().getId()));
                tfCompany.setText(String.valueOf(row.getItem().getCompanyName()));
                tfPrice.setText(String.valueOf(row.getItem().getPrice()));
                cboStartCity.setValue(String.valueOf(row.getItem().getStartCity()));
                cboEndCity.setValue(String.valueOf(row.getItem().getEndCity()));
                cboSchedule.setValue(String.valueOf(row.getItem().getSchedule()));
            });
            return row;
        });

        btnInsert.setOnAction(e->{
            Route.addRoute(Double.parseDouble(tfPrice.getText()),Timetable.getIdByName(tfCompany.getText()),Cities.getIdByName(cboStartCity.getValue()),Cities.getIdByName(cboEndCity.getValue()),Timetable.getIdByName(cboSchedule.getValue()));
            Route.showRoutes(routesTable);
        });

        btnDelete.setOnAction(e->{
            Route.deleteRoute(Integer.parseInt(tfId.getText()));
            Route.showRoutes(routesTable);
        });

        btnUpdate.setOnAction(e->{
            Route.updateRoute(Integer.parseInt(tfId.getText()),Double.parseDouble(tfPrice.getText()), Timetable.getIdByName(tfCompany.getText()),Cities.getIdByName(cboStartCity.getValue()),Cities.getIdByName(cboEndCity.getValue()),Timetable.getIdByName(cboSchedule.getValue()));
            Route.showRoutes(routesTable);
        });

    }



    public ComboBox<String> getCboStartCity() {
        return cboStartCity;
    }

    public ComboBox<String> getCboEndCity() {
        return cboEndCity;
    }

    public ComboBox<String> getCboSchedule() {
        return cboSchedule;
    }

    public TextField getTfCompany() {
        return tfCompany;
    }


    public TextField getTfId() {
        return tfId;
    }

    public TextField getTfPrice() {
        return tfPrice;
    }

    public Button getBtnClear() {
        return btnClear;
    }

    public Button getBtnDelete() {
        return btnDelete;
    }

    public Button getBtnInsert() {
        return btnInsert;
    }

    public Button getBtnUpdate() {
        return btnUpdate;
    }
}
