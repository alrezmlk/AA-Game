package model.animation;

import controller.GameController;
import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.TableRow;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import model.Ball;

public class GameWinAnimation extends Transition {
    private Group group;
    private Pane pane;

    public GameWinAnimation(Group group, Pane pane) {
        this.group = group;
        this.pane = pane;
        this.setCycleCount(1);
        this.setCycleDuration(Duration.seconds(2));
    }

    @Override
    protected void interpolate(double v) {
        pane.setBackground(Background.fill(Color.GREEN));

        for (Node child : group.getChildren()) {
            if(child instanceof Ball) {
                Ball ball = (Ball) child;
                if(ball.getRadius() > 15) {
                    ball.setScaleX(1 + v);
                    ball.setScaleY(1 + v);
                    ball.setCenterY(ball.getCenterY() - 5);
                    continue;
                }
                double x = ball.getCenterX() - GameController.centerX;
                double y = ball.getCenterY() - GameController.centerY;
                ball.setCenterY(ball.getCenterY() + y / 40);
                ball.setCenterX(ball.getCenterX() + x / 40);
                if(ball.getNumberLabel() == null) continue;
                ball.getNumberLabel().setLayoutY(ball.getCenterY() + y /20);
                ball.getNumberLabel().setLayoutX(ball.getCenterX() + x / 20);
            }
            if(child instanceof Line) {
                Line line = (Line) child;
                double x = line.getEndX() - GameController.centerX;
                double y = line.getEndY() - GameController.centerY;
//                line.setStartX(line.getStartX() + x / 10);
//                line.setStartY(line.getStartY() + y / 10);
                line.setScaleX(1 + 3 * v);
                line.setScaleY(1 + 3 * v);

            }
        }
    }
}
