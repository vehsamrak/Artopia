package artopia.models;

/**
 * @author Rottenwood
 */
public class User {
    public final String username;
    private boolean isAuthenticated = false;

    // TODO: 22.12.15 Обработка пароля
    public User(String username, String password) {
    String usernameInLowercase = username.toLowerCase();
    username = Character.toString(usernameInLowercase.charAt(0)).toUpperCase() + usernameInLowercase.substring(1);

        this.username = username.toLowerCase();
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void authenticate() {
        isAuthenticated = true;
    }
}
