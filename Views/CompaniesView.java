package Views;

import Models.*;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class CompaniesView extends VBox {
//name of the company, email, username, number of routes

    private TextField txtComName, txtNo_buses, tfUsername;
    private TableView userTbl = new TableView();


    public CompaniesView() {


        //kolona per emrin e kompanise
        TableColumn<String, Companies> comNameColumn = new TableColumn<>("Name of the company");
        comNameColumn.setMinWidth(57 + 175);
        comNameColumn.setCellValueFactory(new PropertyValueFactory("companyName")); //Emri i kompanise prej databazes

        //kolona per username
        TableColumn<String, Companies> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setMinWidth(57 + 175);
        usernameColumn.setCellValueFactory(new PropertyValueFactory("username"));//Username

        //kolona per emailin
        TableColumn<Integer, Companies> no_busesColumn = new TableColumn<>("Number of buses");
        no_busesColumn.setMinWidth(57 + 175);
        no_busesColumn.setCellValueFactory(new PropertyValueFactory("no_buses"));//Emaili


        userTbl.getColumns().addAll(comNameColumn, usernameColumn, no_busesColumn);


        Companies.showRoutes(userTbl);
        setPadding(new Insets(20));

////////////////////////Pjesa e editueshme nga admini//////////////////////////////////////////

        //Emri i kompanise
        txtComName = new TextField();
        txtComName.setPromptText("Company name");
        txtComName.setMinWidth(100);

        //Username
        tfUsername = new TextField();
        tfUsername.setPromptText("Username");
        tfUsername.setMinWidth(100);

        //Numri i buseve
        txtNo_buses = new TextField();
        txtNo_buses.setPromptText("Number of buses");
        txtNo_buses.setMinWidth(100);


        //buttonat
        Button addButton = new Button("   Add   ");
        Button updateButton = new Button(" Update ");
        Button deleteButton = new Button(" Delete ");

        deleteButton.setOnAction( e -> {
            deleteVoid();
        });

        HBox hBox = new HBox(10);
        hBox.setPadding(new Insets(10, 10, 10, 10));
        hBox.getChildren().addAll(txtComName, tfUsername, txtNo_buses,deleteButton);

/////////////////////Perfundimi i pjeses se editueshme nga admini/////////////////////////////////////
        getChildren().addAll(userTbl, hBox);

        userTbl.setRowFactory(tv -> {

            TableRow<Companies> row = new TableRow<>();

            row.setOnMouseClicked(event -> {
                txtComName.setText(String.valueOf(row.getItem().getCompanyName()));
                txtNo_buses.setText(String.valueOf(row.getItem().getNo_buses()));
                tfUsername.setText(String.valueOf(row.getItem().getUsername()));
            });

            return row;
        });

    }
    public void deleteVoid() {
        if(Companies.deleteRecord(txtComName.getText())) {
            Companies.showRoutes(userTbl);
        }
    }

}


