package view;

import controller.UserController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Avatar;

import java.util.Arrays;

public class AvatarMenu extends Application {
    @FXML
    public HBox firstBox;
    @FXML
    public HBox secondBox;
    @FXML
    public ImageView avatar;
    @FXML
    public Label username;


    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane avatarPane = FXMLLoader.load(AvatarMenu.class.getResource("/fxml/AvatarChoose.fxml"));

        Scene scene = new Scene(avatarPane);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void initialize() {
        username.setText(UserController.getLoggedInUser().getUsername());
        avatar.setImage(UserController.getLoggedInUser().avatar.image);
    }

    public void backToProfileMenu(MouseEvent mouseEvent) throws Exception {
        new ProfileMenu().start(LoginMenu.stage);
    }

    public void chooseAvatar(MouseEvent keyEvent) throws Exception {
        int index = Integer.parseInt(((ImageView)keyEvent.getSource()).getId());
        UserController.getLoggedInUser().setAvatar(Avatar.values()[index-1]);
        new ProfileMenu().start(LoginMenu.stage);
    }

//    public void addAvatars() {
//        firstBox.setSpacing(15);
//        secondBox.setSpacing(15);
//        for(int i = 0; i < 4; i++) {
//            if(i)
//            ImageView imageView = new ImageView(a)
//        }
//    }
}
