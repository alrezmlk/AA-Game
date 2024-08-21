package view;

import controller.GameController;
import enums.GameStatus;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class EndGame extends Application {
    private GameStatus gameStatus;
    private static Stage dialogStage;
    @FXML
    public ImageView gameStatusImageView;
    @FXML
    public Label score;


    @Override
    public void start(Stage stage) throws Exception {
        this.gameStatus = GameController.game.getGameStatus();
        System.out.println("start am");
        dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.initOwner(stage);
        AnchorPane gameStatusPane = FXMLLoader.load(EndGame.class.getResource("/fxml/EndGame.fxml"));

        gameStatusPane.setBackground(Background.fill(GameController.game.getGameStatus().color));
        Scene scene = new Scene(gameStatusPane);
        dialogStage.setScene(scene);
        dialogStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                dialogStage.close();

                if(gameStatus.equals(GameStatus.PAUSE)) {
                    try {
                        GameController.continueGame();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                else {
                    try {
                        new MainMenu().start(LoginMenu.stage);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        dialogStage.show();
    }

    @FXML
    public void initialize() {
        System.out.println("initialize am");
        this.gameStatus = GameController.game.getGameStatus();
        gameStatusImageView.setImage(gameStatus.image);

        if(gameStatus.equals(GameStatus.PAUSE)) {
            gameStatusImageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    dialogStage.close();
                    try {
                        GameController.continueGame();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }


        score.setText(String.valueOf(GameController.game.getScore()));

    }

    public void startGame(MouseEvent mouseEvent) throws Exception {
        dialogStage.close();
        new StartGame().start(LoginMenu.stage);
    }

    public void startMainMenu(MouseEvent mouseEvent) throws Exception {
        dialogStage.close();
        new MainMenu().start(LoginMenu.stage);
    }
}
