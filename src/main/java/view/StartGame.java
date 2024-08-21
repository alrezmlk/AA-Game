package view;

import controller.GameController;
import enums.GameMap;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import model.Ball;
import model.Game;
import model.animation.RotateTrans;

public class StartGame extends Application {
    public Group group;
    public Pane pane;
    public static int centerX = 400;
    public static int centerY = 260;
    @FXML
    public ProgressBar freezeBar;
    @FXML
    public Label throwDegree;
    @FXML
    public ImageView pauseIV;
    @FXML
    public Label scoreLabel;


    @Override
    public void start(Stage stage) throws Exception {
        Pane gamePane = FXMLLoader.load(StartGame.class.getResource("/fxml/Game.fxml"));
        pane = gamePane;
        Scene scene = new Scene(gamePane);
        stage.setScene(scene);

        gamePane.setBackground(Background.fill(Color.ROSYBROWN));
        group = setFirstBalls(GameController.getSelectedGameMap());
        gamePane.getChildren().add(group);

        initializingController(gamePane, group);
        RotateTrans rotateTrans = new RotateTrans(group, pane, 1.5);
        buildTimer();
        GameController.initializeTimerTimeLine();
        rotateTrans.play();
        GameController.rotateTrans = rotateTrans;
        stage.show();
    }

    @FXML
    public void initialize() {
//        if(freezeBar == null){
//            System.out.println("ey baba");
//            freezeBar = new ProgressBar(0);
//        }
        freezeBar.setProgress(0);
        GameController.setBar(freezeBar);
        GameController.setDegreeLabel(throwDegree);
        GameController.setScoreLabel(scoreLabel);

        pauseIV.setImage(new Image(StartGame.class.getResource("/image/stop.jpg").toExternalForm()));


    }

    public void initializingController(Pane pane, Group group) {
        GameController.getBalls().clear();
        GameController.game =new Game(GameController.getSelectedGameLevel(), GameController.getSelectedGameMap(), 5);
        GameController.setPane(pane);
        GameController.setGroup(group);

        Ball firstBall = new Ball(GameController.game.getBallsLeft(), 400, pane);
        GameController.getBalls().add(firstBall);
//        System.out.println("balls count: " + GameController.getBalls().size());
        GameController.setEvent();

    }
    public void buildTimer() {
        Label label = new Label("time ");
        label.setLayoutX(10);
        label.setLayoutY(560);
        label.setFont(Font.font(19));
        pane.getChildren().add(label);
        Label timeLabel = new Label("00:00");
        timeLabel.setLayoutX(50);
        timeLabel.setLayoutY(560);
        timeLabel.setFont(Font.font(20));
        pane.getChildren().add(timeLabel);
        GameController.setTimeLabel(timeLabel);

    }


    public Group setFirstBalls(GameMap gameMap) {
        double degree;
        Group group = new Group();
        Ball major = new Ball(centerX, centerY, 40);
        group.getChildren().add(major);
        if(gameMap.equals(GameMap.FIRST)) {
            for (int i = 0; i < 6; i++) {
                addBallAndLine(group, (double) (360 / 6) * i);
            }
        }
        if(gameMap.equals(GameMap.SECOND)) {
            degree = addBallAndLine(group, 0);
            degree = addBallAndLine(group, degree + 40);
            degree = addBallAndLine(group,degree + 100);
            degree = addBallAndLine(group, degree + 40);
            degree = addBallAndLine(group, degree + 40);
            addBallAndLine(group, degree + 100);
        }
        if(gameMap.equals(GameMap.THIRD)) {
            addBallAndLine(group, 0);
            degree = addBallAndLine(group, 90);
            for (int i = 1; i <= 3; i++) {
                degree = addBallAndLine(group, degree + i * 60);
            }
            addBallAndLine(group, degree + 90);
        }

        return group;
    }
    public double addBallAndLine(Group group, double rotateAngle) {
        Ball ball = new Ball(centerX, 400, 10);
        Line line = GameController.buildALine(centerX, 400);
        Rotate rotate = new Rotate(rotateAngle, centerX, centerY);
        ball.getTransforms().add(rotate);
        line.getTransforms().add(rotate);
        group.getChildren().addAll(ball, line);
        return rotateAngle;
    }


    public void pauseGame(MouseEvent mouseEvent) throws Exception {
        GameController.pauseGame();
    }
}
