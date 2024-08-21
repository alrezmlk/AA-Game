package view;

import controller.UserController;
import enums.AlertMassage;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RegisterMenu extends Application {
    public static Stage stage;
    public Pane root;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField passwordAgain;
    @FXML
    private Text alert;


    @Override
    public void start(Stage stage) throws Exception {
        RegisterMenu.stage = stage;
        BorderPane registerPane = FXMLLoader.load(RegisterMenu.class.getResource("/fxml/RegisterMenu.fxml"));
        root = registerPane;
        Scene scene = new Scene(registerPane);
        stage.setScene(scene);
        stage.show();
    }

    public void register(MouseEvent mouseEvent) {
        AlertMassage massage = UserController.register(username.getText(), password.getText(), passwordAgain.getText());
            emptyFields();
            alert.setText(massage.response);

    }
    public void emptyFields() {
        this.username.setText("");
        this.password.setText("");
        this.passwordAgain.setText("");
    }

    public void backToLoginMenu(MouseEvent mouseEvent) throws Exception {
        new LoginMenu().start(RegisterMenu.stage);
    }
}
