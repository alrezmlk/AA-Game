package model;

import controller.GameController;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball extends Circle {
    private Label numberLabel = null;
    public Ball(double x, double y, double r) {
        super(x, y, r);
    }
    public Ball(double x , double y, int number, Pane pane){
        super(x , y , 10);
        this.numberLabel = new Label(String.valueOf(number));
        numberLabel.setLayoutX(400-5);
        numberLabel.setLayoutY(550-5);
        //numberLabel.setLabelFor(this);
        numberLabel.setTextFill(Color.WHITE);
        pane.getChildren().add(this);
        pane.getChildren().add(numberLabel);
    }
    public Ball(int number, double x, Pane pane) {
        super(400, 550, 10);
        this.numberLabel = new Label(String.valueOf(number));
        numberLabel.setLayoutX(x-5);
        numberLabel.setLayoutY(550-5);
        numberLabel.setLabelFor(this);
        numberLabel.setTextFill(GameController.game.getGameFaze().leftBallsColor);

        pane.getChildren().add(this);
        pane.getChildren().add(numberLabel);
    }

    public Label getNumberLabel() {
        return numberLabel;
    }
}
