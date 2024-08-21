//package model.animation;
//
//import controller.GameController;
//import javafx.animation.Transition;
//import javafx.scene.Group;
//import javafx.scene.Node;
//import javafx.scene.control.Label;
//import javafx.scene.layout.Pane;
//import javafx.scene.shape.Line;
//import javafx.util.Duration;
//import model.Ball;
//
//import static java.lang.Math.toDegrees;
//
//public class Rotationer extends Transition {
//    private Group group;
//    private Pane pane;
//
//    public Rotationer(Group group, Pane pane) {
//        this.group = group;
//        this.pane = pane;
//        this.setCycleCount(-1);
//        this.setCycleDuration(Duration.seconds(5));
//        this.setRate(10);
//    }
//
//    @Override
//    protected void interpolate(double v) {
//        double x , y, degree, newDeg, newX, newY;
//
//        for (Node child : group.getChildren()) {
//            if(child instanceof Ball) {
//                Ball ball = (Ball) child;
//                y = ball.getCenterY() - GameController.centerY;
//                x = ball.getCenterX() - GameController.centerX;
//                if(ball.getRadius() > 15) continue;
//                degree = toDegrees(Math.atan(y / x));
//                //System.out.println("y , x " +y + " " + x + "radius: " + ball.getRadius());
//                //System.out.println("degree" + degree);
//                newDeg = Math.toRadians(degree + 1);
//                newX = Math.cos(newDeg) * 140 + GameController.centerX;
//                newY = Math.sin(newDeg) * 140 + GameController.centerY;
//                ball.setCenterY(newY);
//                ball.setCenterX(newX);
////                ball.getNumberLabel().setLayoutY(newY);
////                ball.getNumberLabel().setLayoutX(newX);
//            }
//            if(child instanceof Line) {
//                Line line = (Line) child;
//                x = line.getEndX() - GameController.centerX;
//                y = line.getEndY() - GameController.centerY;
//                degree = toDegrees(Math.atan(y / x));
//                newDeg = Math.toRadians(degree + 1);
//                newX = Math.cos(newDeg) * 140 + GameController.centerX;
//                newY = Math.sin(newDeg) * 140 + GameController.centerY;
//                line.setEndY(newY);
//                line.setEndX(newX);
//            }
//            if(child instanceof Label) {
//                Label label = (Label) child;
//                newDeg = getNewAngle(label.getLayoutX(), label.getLayoutY());
//                label.setLayoutX(Math.cos(newDeg) * 140 + GameController.centerX);
//                label.setLayoutY(Math.sin(newDeg) * 140 + GameController.centerY);
//
//            }
//        }
//    }
//    public double getNewAngle(double x , double y) {
//        double degree = toDegrees(Math.atan(y - GameController.centerY / x - GameController.centerX));
//        return Math.toRadians(degree + 1);
//    }
//}
