package model.animation;

import controller.GameController;
import javafx.animation.Transition;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class RotateTrans extends Transition {
    private Group group;
    private Pane pane;
    private double angleChange;
    private double rotateUpToNow = 0;

    public RotateTrans(Group group, Pane pane, double change) {
        this.group = group;
        this.pane = pane;
        this.angleChange = change;
        this.setCycleCount(-1);
        this.setCycleDuration(Duration.seconds(5));

    }

    @Override
    protected void interpolate(double v) {
        Rotate rotate = new Rotate(angleChange, GameController.centerX, GameController.centerY);
        Rotate rotate1 = new Rotate();
        rotate1.setPivotY(GameController.centerY - 400);
        rotate1.setPivotX(GameController.centerX - 400);
        rotate1.setAngle(angleChange);
        rotateUpToNow += angleChange;
        for (Node child : group.getChildren()) {
            if(child instanceof Label){
                child.getTransforms().add(rotate1);
                continue;
            }
            child.getTransforms().add(rotate);
        }
        //if(this.getCycleCount() > 3) this.stop();
    }

    public double getAngleChange() {
        return angleChange;
    }

    public void setAngleChange(double angleChange) {
        this.angleChange = angleChange;
    }

    public double getRotateUpToNow() {
        return rotateUpToNow;
    }
}
