package model;

import javafx.scene.image.Image;

import java.util.Random;

public enum Avatar {
    OLD_MAN(new Image(Avatar.class.getResource("/image/1-01.jpg").toExternalForm())),
    LITTLE_GIRL(new Image(Avatar.class.getResource("/image/1-02.jpg").toExternalForm())),
    MASK_MAN(new Image(Avatar.class.getResource("/image/1-03.jpg").toExternalForm())),
    MUSIC_BOY(new Image(Avatar.class.getResource("/image/1-04.jpg").toExternalForm())),
    THE_VAMPIRE(new Image(Avatar.class.getResource("/image/1-05.jpg").toExternalForm())),
    CAT_GIRL(new Image(Avatar.class.getResource("/image/1-06.jpg").toExternalForm())),
    WALTER_WHITE(new Image(Avatar.class.getResource("/image/1-07.jpg").toExternalForm())),
    ;
    public final Image image;

    Avatar(Image image) {
        this.image = image;
    }
    public static Avatar getRandomAvatar() {
        return Avatar.values()[new Random().nextInt(Avatar.values().length)];
    }

}
