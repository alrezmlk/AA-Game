package view;

import controller.UserController;
import enums.AlertMassage;
import enums.GameLevel;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Avatar;

public class ProfileMenu extends Application {
    public static Scene scene;
    //public Label massageToUser;
    public ImageView avatarImage;

    public Label username;
    public Label userPassword;
    public Label score;

    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane profilePane = FXMLLoader.load(ProfileMenu.class.getResource("/fxml/ProfileMenu.fxml"));
        Scene scene = new Scene(profilePane);
        stage.setScene(scene);
        ProfileMenu.scene = scene;
        stage.setTitle("Profile Menu");
        stage.show();
    }
    @FXML
    public void initialize() {
        avatarImage.setImage(UserController.getLoggedInUser().getAvatar().image);
        username.setText(UserController.getLoggedInUser().getUsername());
        userPassword.setText(UserController.getLoggedInUser().getPassword());
        score.setText(String.valueOf(UserController.getLoggedInUser().getScore(GameLevel.TOTAL)));
    }
    @FXML
    public void back(MouseEvent mouseEvent) {
        LoginMenu.stage.setScene(MainMenu.scene);
    }


    public DialogPane getImages() {
        HBox box = new HBox();
        for (Avatar value : Avatar.values()) {
            box.getChildren().add(new ImageView(value.image));
        }
        box.setSpacing(20);
        AnchorPane anchorPane = new AnchorPane(box);
        DialogPane dialogPane = new DialogPane();
        dialogPane.getChildren().add(anchorPane);
        return dialogPane;
    }

    public void changeAvatar(MouseEvent mouseEvent) throws Exception {
        new AvatarMenu().start(LoginMenu.stage);
    }

    public void changeUserName(MouseEvent mouseEvent) {
        TextInputDialog usernameInput = new TextInputDialog(UserController.getLoggedInUser().getUsername());
        usernameInput.setContentText("please enter your new username");
        usernameInput.setTitle("username");
        usernameInput.showAndWait();
        System.out.println(usernameInput.getEditor().getText());
        AlertMassage A = UserController.changeUsername(usernameInput.getEditor().getText());
        initialize();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(A.response);
        alert.show();
    }
    public void changePassword(MouseEvent mouseEvent) {
        TextInputDialog usernameInput = new TextInputDialog(UserController.getLoggedInUser().getPassword());
        usernameInput.setContentText("please enter your new password");
        usernameInput.setTitle("password");
        usernameInput.showAndWait();
        System.out.println(usernameInput.getEditor().getText());
        AlertMassage A = UserController.changePassword(usernameInput.getEditor().getText());
        initialize();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(A.response);
        alert.show();
    }

    public void deleteAccount(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.NONE, "delete account", ButtonType.YES, ButtonType.CANCEL);
        alert.setContentText("are you sure you want to delete your account??");
        alert.setTitle("delete account");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                try {
                    AlertMassage a = UserController.deleteAccount();
                    //LoginMenu.initialize(a.response);
                    LoginMenu.stage.setScene(LoginMenu.scene);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else if(response == ButtonType.CANCEL)
                alert.close();
        });
    }

    public void logout(MouseEvent mouseEvent) {
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
