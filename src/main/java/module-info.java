module AA {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires com.google.gson;

    exports enums;
    exports view;
    exports controller;
    opens controller to javafx.fxml;
    opens view to javafx.fxml;
    exports model;
    opens model to com.google.gson;
}