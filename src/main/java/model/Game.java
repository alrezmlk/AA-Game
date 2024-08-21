package model;

import controller.GameController;
import enums.GameFaze;
import enums.GameLevel;
import enums.GameMap;
import enums.GameStatus;

import java.util.ArrayList;

public class Game {
    private int numberOfStarterBalls;
    private User player;
    private GameLevel gameLevel;
    private GameMap gameMap;
    private int ballsLeft;
    private final int totalBalls;
    private long timeLeft;
    private double score = 0;
    private int ballsInARow = 1;
    private int quarterBalls = 0;
    private double throwDegree = 0;
    private GameStatus gameStatus;
    public double xOFThrowPoint = GameController.centerX;
    private GameFaze gameFaze;
    private ArrayList<Double> rotationOfBalls = new ArrayList<>();
    public int getBallsInARow() {
        return ballsInARow;
    }
    public void addBallsInARow() {
        this.ballsInARow += 1;
    }
    public void resetBallInARow() {
        this.ballsInARow = 0;
    }

    public Game(GameLevel gameLevel, GameMap gameMap, int totalBalls) {
        this.gameLevel = gameLevel;
        this.gameMap = gameMap;
        this.gameStatus = GameStatus.PAUSE;
        this.gameFaze = GameFaze.FAZE4;
        this.totalBalls = totalBalls;
        this.ballsLeft = totalBalls;
        this.timeLeft = 60000;

    }

    public GameLevel getGameLevel() {
        return gameLevel;
    }

    public double getScore() {
        return score;
    }

    public void addToScore() {
        for (int i = 0; i < GameFaze.values().length; i++) {
            if(gameFaze.equals(GameFaze.values()[i])) score += i + 2;
        }
        score = score + (int)((totalBalls - ballsLeft) / 3);
        //todo complete
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public boolean addQuarterBalls() {
        quarterBalls++;
        if(quarterBalls > (double) totalBalls / 4) {
            System.out.println("quarter balls : " + quarterBalls);
            quarterBalls = 0;

            //this.changeFaze();
            return true;
        }
        return false;
    }
    public void changeFaze() {
        if (gameFaze == GameFaze.FAZE4) return;
        gameFaze = gameFaze.getNextFaze();
    }

    public GameFaze getGameFaze() {
        return gameFaze;
    }
    public void removeFromBallsLeft() {
        this.ballsLeft--;
    }
    public int getBallsLeft() {
        return ballsLeft;
    }

    public double getThrowDegree() {
        return throwDegree;
    }

    public void setThrowDegree(double throwDegree) {
        this.throwDegree = throwDegree;
    }

    public ArrayList<Double> getRotationOfBalls() {
        return rotationOfBalls;
    }

    public void setTimeLeft(long timeLeft) {
        this.timeLeft = timeLeft;
    }

    public long getTimeLeft() {
        return timeLeft;
    }
}
