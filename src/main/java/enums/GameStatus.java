package enums;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public enum GameStatus {
    WIN(new Image(GameStatus.class.getResource("/image/win.png").toExternalForm()), Color.LIMEGREEN),
    LOSS(new Image(GameStatus.class.getResource("/image/lose.jpg").toExternalForm()), Color.INDIANRED),
    PAUSE(new Image(GameStatus.class.getResource("/image/continue.jpg").toExternalForm()), Color.SLATEGRAY);
    public Image image;
    public Color color;
    GameStatus(Image image, Color color) {
        this.image = image;
        this.color = color;
    }
}
