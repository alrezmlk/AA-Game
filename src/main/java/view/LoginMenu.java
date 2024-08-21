package view;

import controller.UserController;
import enums.AlertMassage;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;

public class LoginMenu extends Application {


    private UserController userController;
    public static Stage stage; // why static????
    public static Scene scene;
    @FXML
    public TextField username;
    @FXML
    public TextField password;
    @FXML
    public Label massageToUser;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stageSettings(stage);
        LoginMenu.stage = stage;
        BorderPane loginPane = FXMLLoader.load(new URL(LoginMenu.class.getResource("/fxml/LoginMenu.fxml").toExternalForm()));
        Scene scene = new Scene(loginPane);
        LoginMenu.scene = scene;
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void initialize(String massage) {
        this.massageToUser.setText(massage);
    }
    public void stageSettings(Stage stage) {
        stage.getIcons().add(new Image(LoginMenu.class.getResource("/image/Icon.jpg").toExternalForm()));
        //stage.setTitle("AA");
        //stage.initStyle(StageStyle.DECORATED);
        UserController.loadUsers();
    }

    public void startRegisterMenu(MouseEvent mouseEvent) throws Exception {
        new RegisterMenu().start(LoginMenu.stage);
    }

    public void login(MouseEvent mouseEvent) throws Exception {
        AlertMassage massage = UserController.login(username.getText(), password.getText());
        if(!massage.equals(AlertMassage.LOGGED_IN)) massageToUser.setText(massage.response);
        else new MainMenu().start(LoginMenu.stage);
    }

    public void newGame(MouseEvent mouseEvent) throws Exception {
        new StartGame().start(LoginMenu.stage);
    }
//    public static void setMassageToUser(String massage) {
//    }
}
