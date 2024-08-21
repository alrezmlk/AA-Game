package enums;

import javafx.scene.paint.Color;
import model.Game;

public enum GameFaze {
    Faze1(Color.WHITE),
    FAZE2(Color.LIGHTBLUE),
    FAZE3(Color.GOLD),
    FAZE4(Color.GREEN);
    public Color leftBallsColor;

    GameFaze(Color leftBallsColor) {
        this.leftBallsColor = leftBallsColor;
    }

    public GameFaze getNextFaze() {
        if(this.equals(Faze1)) return FAZE2;
        if(this.equals(FAZE2)) return FAZE3;
        if(this.equals(FAZE3)) return FAZE4;
        return null;
    }
}
