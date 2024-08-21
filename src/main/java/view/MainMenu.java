package view;

import controller.GameController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Game;

import java.util.Collections;

public class MainMenu extends Application {
    public static Scene scene;


    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane mainPane = FXMLLoader.load(MainMenu.class.getResource("/fxml/MainMenu.fxml"));
        Scene scene = new Scene(mainPane);
        stage.setScene(scene);
        MainMenu.scene = scene;
        stage.show();
    }

    public void startNewGame(MouseEvent mouseEvent) throws Exception {
        new StartGame().start(LoginMenu.stage);
    }

    public void startProfileMenu(MouseEvent mouseEvent) throws Exception {
        new ProfileMenu().start(LoginMenu.stage);
    }

    public void startScoreBoard(MouseEvent mouseEvent) throws Exception {
        new ScoreBoard().start(LoginMenu.stage);
    }

    public void startSettings(MouseEvent mouseEvent) throws Exception {
        new Settings().start(LoginMenu.stage);
    }

    public void continuePreviousGame(MouseEvent mouseEvent) {
        // todo read the saved game from the file
        Game game = new Game(null ,null, 20);
        if(GameController.game != null) System.out.println("game is not null");
        GameController.game = game;

    }

    public void logout(MouseEvent mouseEvent) throws Exception {
        Alert alert = new Alert(Alert.AlertType.NONE, "are you sure you want to logout?", ButtonType.YES, ButtonType.NO);
        alert.setContentText("are you sure you want to logout?");
        alert.setTitle("logout");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                try {
                    new LoginMenu().start(LoginMenu.stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else if(response == ButtonType.CANCEL) alert.close();
        });
    }
}
