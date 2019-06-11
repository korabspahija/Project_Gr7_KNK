import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


public class User extends VBox {
//name of the company, email, username, number of routes

    private TextField txtComName, txtEmail, tfUsername;

    private TableView userTbl = new TableView();


    public User(){


        //kolona per emrin e kompanise
        TableColumn comNameColumn = new TableColumn<>("Name of the company");
        comNameColumn.setMinWidth(175);
        comNameColumn.setCellValueFactory(new PropertyValueFactory("company name")); //Emri i kompanise prej databazes

        //kolona per emailin
        TableColumn emailColumn = new TableColumn<>("Email");
        emailColumn.setMinWidth(175);
        emailColumn.setCellValueFactory(new PropertyValueFactory("Email"));//Emaili

        //kolona per username
        TableColumn usernameColumn = new TableColumn<>("Username");
        usernameColumn.setMinWidth(175);
        usernameColumn.setCellValueFactory(new PropertyValueFactory("Username"));//Username

        //kolona per username
        TableColumn numberOfRoutesColumn = new TableColumn<>("No.Routes");
        numberOfRoutesColumn.setMinWidth(175);
        numberOfRoutesColumn.setCellValueFactory(new PropertyValueFactory("No.Routes"));//Numri i linjave


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
        hBox.getChildren().addAll(txtComName,txtEmail, tfUsername, addButton, updateButton, deleteButton);

/////////////////////Perfundimi i pjeses se editueshme nga admini/////////////////////////////////////
        userTbl.getColumns().addAll(comNameColumn, emailColumn, usernameColumn, numberOfRoutesColumn);
        userTbl.setPrefHeight(400);
        userTbl.setPrefWidth(800);
        getChildren().addAll(userTbl, hBox);
    }
}

