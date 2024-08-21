package model.animation;

import controller.GameController;
import javafx.animation.Transition;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Ball;
import model.Game;


public class MagnifyingTransition extends Transition {
    private Group group;

    public MagnifyingTransition(Group group) {
        this.group = group;
        this.setCycleCount(-1);
        this.setCycleDuration(Duration.seconds(4));
    }

    @Override
    protected void interpolate(double v) {
        for (Node child : group.getChildren()) {
            if(!(child instanceof Ball)) continue;
            Ball ball = (Ball) child;
            if(ball.getRadius() > 20) continue;
            if(v > 0.7) {
//                ball.setScaleY(1);
//                ball.setScaleX(1);
                ball.setRadius(10);
                continue;
            }
//            ball.setScaleY(1 + v * 0.2);
//            ball.setScaleX(1 + v * 0.2);
            ball.setRadius(10 * (1 + v * 0.2));
//            for (Node groupChild : group.getChildren()) {
//                if(groupChild instanceof Ball) continue;
//                if(child.equals(groupChild)) continue;
//                if(child.getBoundsInParent().intersects(groupChild.getBoundsInParent())) {
//                    try {
//                        GameController.gameLose();
//                    } catch (Exception e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//            }
        }
    }
}
