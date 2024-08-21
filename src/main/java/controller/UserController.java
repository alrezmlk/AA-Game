package controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import enums.AlertMassage;
import model.Avatar;
import model.User;
import java.io.FileReader;
import java.io.Reader;
import java.io.*;

import com.google.gson.Gson;


public class UserController {
    private static User loggedInUser;

    public static AlertMassage register(String username, String password, String repeatedPass) {
        if(username.isEmpty() || password.isEmpty() || repeatedPass.isEmpty()) return AlertMassage.EMPTY_FIELD;
        if(username.length() < 5) return AlertMassage.SHORT_USERNAME;
        if(password.length() < 5) return AlertMassage.SHORT_PASSWORD;
        if(User.getUserByUsername(username) != null) return AlertMassage.USER_ALREADY_EXISTS;
        User.getUsers().add(new User(username, password, Avatar.getRandomAvatar()));
        saveTheData();
        return AlertMassage.REGISTER_SUCCESSFUL;
    }
    public static AlertMassage login(String username, String password) {
        User user;
        if(username.isEmpty() || password.isEmpty()) return AlertMassage.EMPTY_FIELD;
        if((user = User.getUserByUsername(username)) == null) return AlertMassage.USER_NOT_FOUND;
        if(!user.isPasswordCorrect(password)) return AlertMassage.WRONG_PASSWORD;
        loggedInUser = user;
        return AlertMassage.LOGGED_IN;
    }
    public static AlertMassage deleteAccount() {
        //if(!loggedInUser.getPassword().equals(password)) return AlertMassage.DELETE_ACCOUNT_FAILED;
        User.getUsers().remove(loggedInUser);
        loggedInUser = null;
        saveTheData();
        return AlertMassage.DELETE_ACCOUNT_SUCCESSFUL;
    }

    public static void saveTheData(){
        Gson gson = new Gson();
        String json = gson.toJson(User.getUsers());
        try {
            FileWriter myWriter = new FileWriter("users.json");
            myWriter.write(json);
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void loadUsers() {
        Reader reader;
        try {
            reader = new FileReader("users.json");
        } catch (FileNotFoundException e) {
            return;
        }
        Gson gson = new Gson();
        JsonArray jsonArray = gson.fromJson(reader, JsonArray.class);
        for (JsonElement jsonElement : jsonArray)
            User.getUsers().add(gson.fromJson(jsonElement, User.class));
        printUsers();
    }
    public static void printUsers() {
        for (User user : User.getUsers()) {
            System.out.println(user.getUsername());
        }
    }
    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static AlertMassage changePassword(String text) {
        if(text.length() < 5) return AlertMassage.SHORT_PASSWORD;
        if(loggedInUser.getPassword().equals(text)) return AlertMassage.SAME_PASSWORD;
        loggedInUser.setPassword(text);
        saveTheData();
        return AlertMassage.PASSWORD_CHANGED;
    }
    public static AlertMassage changeUsername(String text) {
        if(text.length() < 5) return AlertMassage.SHORT_USERNAME;
        if(loggedInUser.getUsername().equals(text)) return AlertMassage.SAME_USERNAME;
        if(User.getUserByUsername(text) != null) return AlertMassage.USER_ALREADY_EXISTS;
        loggedInUser.setUsername(text);
        saveTheData();
        return AlertMassage.USERNAME_CHANGED;
    }
    public static AlertMassage logout() {
        return null;
    }
}
