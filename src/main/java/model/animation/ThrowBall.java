package model.animation;

import controller.GameController;
import enums.GameStatus;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.Transition;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.RotateEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.util.Duration;
import model.Ball;
import model.Game;

public class ThrowBall extends Transition {
    private Ball ball;
    private Pane pane;
    private Group group;
    static int i = 0;

    public ThrowBall(Ball ball, Pane pane, Group group) {
        this.ball = ball;
        this.pane = pane;
        this.group = group;
        this.setCycleCount(1);
        this.setCycleDuration(Duration.seconds(3));

    }

    @Override
    protected void interpolate(double v) {
        double y = ball.getCenterY() - 8;
        double x = ball.getCenterX() - (8 * Math.tan(Math.toRadians(GameController.game.getThrowDegree())));


        if(ball.getBoundsInParent().intersects(GameController.circle.getBoundsInParent())) {

            Line line = GameController.buildALine(ball.getCenterX(), ball.getCenterY());

            for (Node child : group.getChildren()) {
                if(!(child instanceof Ball)) continue;
                if(ball.getBoundsInParent().intersects(child.getBoundsInParent())) {
                    //System.out.println("game over");
                    try {
                        GameController.gameLose();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            this.stop();
            group.getChildren().addAll(ball, line);
            group.getChildren().add(ball.getNumberLabel());

//            for (Node child : group.getChildren()) {
//                double rotateAngle = 0;
//                if(!(child instanceof Ball) || ((Ball)child).getRadius() > 10 ) continue;
//                for (Transform transform : ball.getTransforms()) {
//                    if(!(transform instanceof Rotate)) continue;
//                    rotateAngle += ((Rotate)transform).getAngle()
//                }
//            }
            double rotate = GameController.rotateTrans.getRotateUpToNow();
            System.out.println(rotate);
            GameController.game.getRotationOfBalls().add(rotate);
            pane.getChildren().remove(ball);
            pane.getChildren().remove(ball.getNumberLabel());

            GameController.updateAfterThrown();
            //GameController.setEvent();
            //GameController.addNewBall();

        }
        if (y <= 20 || x > 700 || x < 30) {
            this.stop();
            try {
                GameController.gameLose();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
            ball.setCenterY(y);
            ball.setCenterX(x);
            ball.getNumberLabel().setLayoutX(x);
            ball.getNumberLabel().setLayoutY(y);


    }
    public void initialize() {
    }
}
//Ball ball1 = new Ball(GameController.centerX, 400, GameController.getLeftBalls() + 1, pane);
//Rotate rotate = new Rotate(-rotationUpToNow, GameController.centerX, GameController.centerY);
//ball1.getTransforms().add(rotate);
//ball1.getNumberLabel().getTransforms().add(rotate);
//line.getTransforms().add(rotate);
//ball.getTransforms().add(rotate);
//GameController.transition.setAxis(new Point3D(GameController.centerX, GameController.centerY, 0));
//GameController.transition.setDuration(Duration.seconds(20));