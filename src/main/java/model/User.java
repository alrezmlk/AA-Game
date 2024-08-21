package model;

import enums.GameLevel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class User {
    private String username;
    private String password;
    public Avatar avatar;
    private final HashMap<GameLevel, Integer> totalScore = new HashMap<>();
    private final HashMap<GameLevel, Integer> totalSeconds = new HashMap<>();


    private int rank = 0;

//    private int totalScore1;
//    private int totalTime;

    private final static ArrayList<User> users = new ArrayList<>();
    public User(String username, String password, Avatar avatar) {
        this.username = username;
        this.password = password;
        this.avatar = avatar;
        initializeHashMap(totalSeconds);
        initializeHashMap(totalScore);

    }
    public void initializeHashMap(HashMap<GameLevel, Integer> hashMap) {
        for (GameLevel value : GameLevel.values()) {
            hashMap.put(value, 0);
        }
    }
    public static ArrayList<User> getUsers() {
        return users;
    }
//    public static ArrayList<User> getSortedUsers() {
//        Collections.sort(users);
//        return users;
//    }
    public static User getUserByUsername(String username) {
        for (User user : users) {
            if(user.username.equals(username)) return user;
        }
        return null;
    }
    public boolean isPasswordCorrect(String password) {
        return this.password.equals(password);
    }
    public String getUsername() { return username;}


    public String getPassword() {
        return password;
    }
    public Avatar getAvatar() {
        return avatar;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void addToTime(GameLevel level, int seconds) {
        totalSeconds.replace(level, totalSeconds.get(level) + seconds);
    }
    public void addToScore(GameLevel level, int score) {
        totalScore.replace(level, totalScore.get(level) + score);
    }
    public int getSeconds(GameLevel level) {
        return totalSeconds.get(level);
    }
    public int getScore(GameLevel level) {
        return totalScore.get(level);
    }
    public static ArrayList<User> getSortedInLevel(GameLevel level) {
        ArrayList<User> sorted = new ArrayList<>(users);
        for(int i = 0; i < sorted.size() - 1; i++) {
            for(int j = 0; j < sorted.size() - i - 1; j++) {
                if(sorted.get(j+1).getScore(level) > sorted.get(j).getScore(level)){
                    Collections.swap(sorted, j , j + 1);
                    continue;
                }
                if(sorted.get(j+1).getScore(level) == sorted.get(j).getScore(level)) {
                    if(sorted.get(j+1).getSeconds(level) < sorted.get(j).getSeconds(level))
                        Collections.swap(sorted, j , j + 1);
                }
            }
        }
        for (User user : sorted) {
            user.setRank(0);
        }
        sorted.get(0).setRank(1);
        if(sorted.size() > 1) sorted.get(1).setRank(2);
        if(sorted.size() > 2) sorted.get(2).setRank(3);
        if(sorted.size() > 10) sorted.removeAll(sorted.subList(10, sorted.size()-1));
        return sorted;
    }

    public HashMap<GameLevel, Integer> getTotalScore() {
        return totalScore;
    }

    public HashMap<GameLevel, Integer> getTotalSeconds() {
        return totalSeconds;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}
