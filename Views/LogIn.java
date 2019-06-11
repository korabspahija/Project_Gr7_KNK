import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;


public class LogIn extends StackPane {
    private TextField tfUsername = new TextField();
    private PasswordField pfPassword = new PasswordField();
    private Button btnLogin = new Button("Login");

    public LogIn() {
        pfPassword.setPromptText("password");
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(15, 10, 15, 10));
        gridPane.setVgap(8);
        gridPane.setHgap(10);


        gridPane.addRow(0, new Label("Username: "), tfUsername);
        gridPane.addRow(1, new Label("Password: "), pfPassword);
        gridPane.addRow(2, btnLogin);


        getChildren().add(gridPane);

        gridPane.setAlignment(Pos.CENTER);
    }

    public TextField getTfUsername() {
        return tfUsername;
    }

    public PasswordField getPfPassword() {
        return pfPassword;
    }

    public Button getBtnLogin() {
        return btnLogin;
    }

    public void setTfUsername(TextField tfUsername) {
        this.tfUsername = tfUsername;
    }

    public void setPfPassword(PasswordField pfPassword) {
        this.pfPassword = pfPassword;
    }

    public void setBtnLogin(Button btnLogin) {
        this.btnLogin = btnLogin;
    }
}