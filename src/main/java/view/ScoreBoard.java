package view;

import enums.GameLevel;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.User;

import java.util.Collections;

public class ScoreBoard extends Application {
    public AnchorPane targetPane;

    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane scoreBoard = FXMLLoader.load(ScoreBoard.class.getResource("/fxml/ScoreBoard.fxml"));
        Scene scene = new Scene(scoreBoard);
        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        targetPane.getChildren().add(tabPane);

        for (GameLevel value : GameLevel.values()) {
            Tab tab = new Tab();
            initializeLists(tab, value);
            tabPane.getTabs().add(tab);
        }


        stage.setScene(scene);
        stage.show();


    }

    public void initializeLists(Tab tab, GameLevel level) {
        tab.setText(level.name());
        ListView<User> listView = new ListView<>();
        listView.setEditable(true);
        listView.setFixedCellSize(40);
        listView.setMinSize(600 , 400);
        ObservableList<User> items = FXCollections.observableArrayList(User.getSortedInLevel(level));
        listView.setItems(items);
        listView.setCellFactory(new Callback<ListView<User>, ListCell<User>>() {
            @Override
            public ListCell<User> call(ListView<User> userListView) {
                return new ListCell<User>() {
                    @Override
                    public void updateItem(User user, boolean empty) {
                        super.updateItem(user, empty);
                        if(user == null || empty) setText(null);
                        else {
                            setText("< " + user.getUsername() + " >  score: " + user.getScore(level) + "  time: " + user.getSeconds(level));
                            if (user.getRank() == 1) setBackground(Background.fill(Color.GOLD));
                            if (user.getRank() == 2) setBackground(Background.fill(Color.SILVER));
                            if (user.getRank() == 3) setBackground(Background.fill(Color.ROSYBROWN)); // BRONZE?
                            setFont(Font.font("times new roman", 16));
                        }
                    }
                };
            }
        });

        tab.setContent(listView);
    }
}
