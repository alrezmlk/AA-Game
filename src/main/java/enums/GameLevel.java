package enums;

public enum GameLevel {
    TOTAL(0, 0 , 0),
    LEVEL1(5, 1.2, 7),
    LEVEL2(10, 1.5, 5),
    LEVEL3(15, 1.8, 3);

    public final int spinningSpeed;
    public final double windSpeed;
    public final int freezeTime;

    GameLevel(int spinningSpeed, double windSpeed, int frozenTimer) {
        this.spinningSpeed = spinningSpeed;
        this.windSpeed = windSpeed;
        this.freezeTime = frozenTimer;
    }


}
