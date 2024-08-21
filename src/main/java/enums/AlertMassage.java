package enums;

public enum AlertMassage {
    EMPTY_FIELD("please make sure you have filled all the necessary fields"),
    USER_ALREADY_EXISTS("this username already exists. please try another username"),
    USER_NOT_FOUND("this username has not registered in the game"),
    SHORT_USERNAME("please choose a longer username"),
    SHORT_PASSWORD("this password is not strong enough please try a longer password"),
    WRONG_PASSWORD("you have entered the wrong password please try again"),
    LOGGED_IN("you are logged in successfully"),
    DELETE_ACCOUNT_FAILED("delete account failed. The password was not correct"),
    DELETE_ACCOUNT_SUCCESSFUL("the account was successfully deleted"),
    REGISTER_SUCCESSFUL("you have successfully signed up in the game. Press back to login"),
    //CHAGNE USERNAME
    SAME_USERNAME("THIS IS YOUR USERNAME RIGHT NOW"),
    SAME_PASSWORD("THIS IS YOUR PASSWORD RIGHT NOW"),
    USERNAME_CHANGED("USERNAME IS CHANGED SUCCESSFULLY"),
    PASSWORD_CHANGED("PASSWORD CHANGED SUCCESSFULLY"),
    ;

    public String response;

    AlertMassage(String response) {
        this.response = response;
    }
}
