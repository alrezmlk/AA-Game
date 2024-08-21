package enums;

public enum GameMap {
    FIRST(6, 120, 0),
    SECOND( 6, 100, 60),
    THIRD(5, 90, 60);

    //private static String url = GameMap.class.getResource("/JSON").toString();

    //private String address;
    private int number;
    private double firstDegree;
    private double secondDegree;

    GameMap(int number, double firstDegree, double secondDegree) {
        //
        //this.address = address;
        this.number = number;
        this.firstDegree = firstDegree;
        this.secondDegree = secondDegree;
    }
}
