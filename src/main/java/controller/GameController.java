package controller;

import enums.GameFaze;
import enums.GameLevel;
import enums.GameMap;
import enums.GameStatus;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import model.Ball;
import model.Game;
import model.animation.GameWinAnimation;
import model.animation.MagnifyingTransition;
import model.animation.RotateTrans;
import model.animation.ThrowBall;
import view.EndGame;
import view.LoginMenu;
import view.MainMenu;
import view.StartGame;

import java.util.ArrayList;
import java.util.Random;

public class GameController {
    public static Game game;
    public static RotateTrans rotateTrans;
    private static Group group;
    private static Pane pane;
    private static Label degreeLabel;
    private static ProgressBar bar;
    private static Label scoreLabel;
    private static Label timeLabel;

    private static ArrayList<Ball> balls = new ArrayList<>();

    private static int BallNumber = 6;
    private static GameLevel selectedGameLevel = GameLevel.LEVEL1;
    private static GameMap selectedGameMap = GameMap.FIRST;
    public static final Circle circle = new Circle(400, 260, 135, Color.BLACK);
    public static final int centerX = 400;
    public static final int centerY = 260;

    public static Group getGroup() {
        return group;
    }


    private static void setGameTimelines() {
        if(game.getGameFaze().equals(GameFaze.FAZE2)) {
            System.out.println("here");
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), actionEvent -> changeDirection()));
            timeline.setCycleCount(1);
            timeline.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    setKeyFrame(timeline);
                }
            });
            timeline.play();

            MagnifyingTransition magnifyingTrans = new MagnifyingTransition(group);
            magnifyingTrans.play();
        }
        if(game.getGameFaze().equals(GameFaze.FAZE3)) {
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), actionEvent -> changeVisibility()));
            timeline.setCycleCount(-1);
            timeline.play();
        }
        if(game.getGameFaze().equals(GameFaze.FAZE4)) {
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(4), actionEvent -> {
                game.setThrowDegree((new Random().nextDouble() - 0.5) * 30);
                degreeLabel.setText(String.valueOf(game.getThrowDegree()));
            }));
            timeline.play();
        }
    }

    public static void initializeTimerTimeLine() {
        long startTime = System.currentTimeMillis();
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                long fullTime = game.getTimeLeft();
                long elapsedMillis = fullTime - (System.currentTimeMillis() - startTime) ;
                timeLabel.setText(elapsedMillis / 1000 +":" + (elapsedMillis % 1000) / 10);
                game.setTimeLeft(elapsedMillis);
                if(elapsedMillis <= 0) {
                    try {
                        gameLose();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }.start();

    }
    public static void setEvent() {

        if(game.getBallsLeft() == 0) return;
        //System.out.println("in a row: " + game.getBallsInARow());
        if(game.getBallsInARow() >= 4) {
            //System.out.println("done");
            pane.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    if(keyEvent.getCode().equals(KeyCode.TAB)) {
                        freeze();
                        game.resetBallInARow();
                        bar.setProgress(0);
                        pane.setOnKeyPressed(null); //todo check if works
                    }
                }
            });
        }

        Ball ball = balls.get(0);

        ball.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(keyEvent.getCode().equals(KeyCode.SPACE)) {
                    System.out.println("space");
                    ThrowBall throwBall = new ThrowBall(ball, pane, group);

                    throwBall.play();
                    removeThrownBall();
                    game.removeFromBallsLeft();
                    GameController.addNewBall();
                    GameController.setEvent();
                    System.out.println("balls left: " + game.getBallsLeft());

                }
                if(game.getGameFaze().equals(GameFaze.FAZE4) && keyEvent.getCode().equals(KeyCode.RIGHT)) {
                    if(ball.getCenterX() < 700) {
                        ball.setCenterX(ball.getCenterX() + 20);
                        ball.getNumberLabel().setLayoutX(ball.getNumberLabel().getLayoutX() + 20);
                        game.xOFThrowPoint = ball.getCenterX();
                    }

                }
                else if(game.getGameFaze().equals(GameFaze.FAZE4) && keyEvent.getCode().equals(KeyCode.LEFT)) {
                    if(ball.getCenterX() > 30) {
                        ball.setCenterX(ball.getCenterX() - 20);
                        ball.getNumberLabel().setLayoutX(ball.getNumberLabel().getLayoutX() - 20);
                        game.xOFThrowPoint = ball.getCenterX();

                    }
                }
            }
        });
        pane.getChildren().get(pane.getChildren().indexOf(ball)).requestFocus();
    }
    public static void addNewBall() {
        System.out.println("left balls: "+ game.getBallsLeft());
        if(game.getBallsLeft() == 0){
            return;
        }
        Ball ball = new Ball(game.getBallsLeft(), game.xOFThrowPoint, pane);
        balls.add(ball);
        System.out.println("left balls" + game.getBallsLeft());
    }

    public static void updateAfterThrown() {
        //removeThrownBall();
        System.out.println(rotateTrans.getCurrentTime());
        System.out.println(game.getBallsLeft());
        if(game.getBallsLeft() == 0) {
            winGame();
        }
        game.addToScore();
        scoreLabel.setText(String.valueOf(game.getScore()));
        game.addBallsInARow();
        boolean fazeChanged = game.addQuarterBalls();
        if(fazeChanged){
            //System.out.println("state changed");
            setGameTimelines();
        }
        fillProgressBar();
    }

    public static void setKeyFrame(Timeline timeline) {
        double random = new Random().nextDouble();
        //timeline.stop();
        timeline.getKeyFrames().setAll(new KeyFrame(Duration.seconds(random * 3 + 3), actionEvent -> changeDirection()));
        //System.out.println("random : " + random);
        timeline.setCycleCount(1);
        timeline.play();

    }

    public static void changeVisibility() {
        for (Node child : group.getChildren()) {
            if(child instanceof Ball && ((Ball)child).getRadius() > 20) continue;
            if(child.isVisible())
                child.setVisible(false);
            else child.setVisible(true);
        }
    }
    private static void changeDirection() {
        rotateTrans.setAngleChange(-rotateTrans.getAngleChange());
    }

    public static void removeThrownBall() {
        balls.remove(0);
    }

    public static void setBar(ProgressBar freezeBar) {
        bar = freezeBar;
    }


    public static void fillProgressBar() {
        bar.setProgress(bar.getProgress() + 0.25);
        if(bar.getProgress() > 0.24) bar.setStyle("-fx-accent: ReD");
        if(bar.getProgress() > 0.49) bar.setStyle("-fx-accent: orange");
        if(bar.getProgress() > 0.74) bar.setStyle("-fx-accent: yellowGreen");
        if(bar.getProgress() > 0.99) bar.setStyle("-fx-accent: green");
    }

    public static void freeze() {
        System.out.println("frozen");
        rotateTrans.stop();

        RotateTrans rotateTrans1 = new RotateTrans(group, pane, 1);
        rotateTrans1.play();
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(game.getGameLevel().freezeTime), actionEvent -> {
            rotateTrans1.stop();
            rotateTrans.play();
            System.out.println(rotateTrans.getCurrentTime());
        });
        Timeline timeline = new Timeline(keyFrame);
        timeline.setCycleCount(1);
        timeline.play();
    }
    public static void winGame() {
        game = null;
        balls.clear();
        game.setGameStatus(GameStatus.WIN);
        GameWinAnimation animation = new GameWinAnimation(group, pane);
        animation.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                rotateTrans.stop();
                try {
                    new EndGame().start(LoginMenu.stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        animation.play();
    }
    public static void gameLose() throws Exception {
        game = null;
        balls.clear();
        GameController.game.setGameStatus(GameStatus.LOSS);
        GameController.rotateTrans.stop();
        new EndGame().start(LoginMenu.stage);
    }
    public static void pauseGame() throws Exception {
        game.setGameStatus(GameStatus.PAUSE);
        rotateTrans.stop();
        new EndGame().start(LoginMenu.stage);
    }
    public static void continueGame() throws Exception {
        rotateTrans.play();
    }


    public static Line buildALine(double x, double y) {
        Line line = new Line();
        line.setStartX(x);
        line.setEndX(centerX);
        line.setStartY(y);
        line.setEndY(centerY);
        return line;
    }


    public static ArrayList<Ball> getBalls() {
        return balls;
    }

    public static void setGroup(Group group) {
        GameController.group = group;
    }

    public static void setPane(Pane pane) {
        GameController.pane = pane;
    }

    public void replayGame(MouseEvent mouseEvent) throws Exception {
        new StartGame().start(LoginMenu.stage);
    }

    public void startMainMenu(MouseEvent mouseEvent) throws Exception {
        new MainMenu().start(LoginMenu.stage);
    }

    public static GameMap getSelectedGameMap() {
        return selectedGameMap;
    }

    public static GameLevel getSelectedGameLevel() {
        return selectedGameLevel;
    }

    public static void setDegreeLabel(Label degreeLabel) {
        GameController.degreeLabel = degreeLabel;
    }

    public static int getBallNumber() {
        return BallNumber;
    }

    public static void setBallNumber(int ballNumber) {
        BallNumber = ballNumber;
    }

    public static void setScoreLabel(Label scoreLabel) {
        GameController.scoreLabel = scoreLabel;
    }

    public static void setTimeLabel(Label timeLabel) {
        GameController.timeLabel = timeLabel;
    }
}
