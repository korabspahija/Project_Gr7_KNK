package Views;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import Models.User;

public class UserView extends VBox {
//name of the company, email, username, number of routes

    private TextField txtComName, txtEmail, tfUsername;
    private TableView userTbl = new TableView();


    public UserView() {


        //kolona per emrin e kompanise
        TableColumn<String, User> comNameColumn = new TableColumn<>("Name of the company");
        comNameColumn.setMinWidth(57 + 175);
        comNameColumn.setCellValueFactory(new PropertyValueFactory("companyName")); //Emri i kompanise prej databazes

        //kolona per emailin
        TableColumn<String, User> emailColumn = new TableColumn<>("Email");
        emailColumn.setMinWidth(57 + 175);
        emailColumn.setCellValueFactory(new PropertyValueFactory("email"));//Emaili

        //kolona per username
        TableColumn<String, User> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setMinWidth(57 + 175);
        usernameColumn.setCellValueFactory(new PropertyValueFactory("username"));//Username



        userTbl.getColumns().addAll(comNameColumn, emailColumn, usernameColumn);


        User.showRoutes(userTbl);


////////////////////////Pjesa e editueshme nga admini//////////////////////////////////////////

        //Emri i kompanise
        txtComName = new TextField();
        txtComName.setPromptText("Company name");
        txtComName.setMinWidth(100);

        //Emaili
        txtEmail = new TextField();
        txtEmail.setPromptText("Email");
        txtEmail.setMinWidth(100);

        //Username
        tfUsername = new TextField();
        tfUsername.setPromptText("Username");
        tfUsername.setMinWidth(100);

        //buttonat
        Button addButton = new Button("   Add   ");
        Button updateButton = new Button(" Update ");
        Button deleteButton = new Button(" Delete ");

        //
        HBox hBox = new HBox(10);
        hBox.setPadding(new Insets(10, 10, 10, 10));
        hBox.getChildren().addAll(txtComName, txtEmail, tfUsername, addButton, updateButton, deleteButton);

/////////////////////Perfundimi i pjeses se editueshme nga admini/////////////////////////////////////
        getChildren().addAll(userTbl, hBox);

    }
}

