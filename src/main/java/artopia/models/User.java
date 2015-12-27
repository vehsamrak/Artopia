package artopia.models;

import artopia.exceptions.EmptyPassword;
import artopia.exceptions.EmptyUsername;

import java.util.Objects;

/**
 * @author Rottenwood
 */
public class User
{
    private final String username;
    private boolean isAuthenticated = false;

    // TODO: 22.12.15 Обработка пароля
    public User(String username, String password) throws EmptyUsername, EmptyPassword {
        if (Objects.equals(username, "")) {
            throw new EmptyUsername();
        } else if (Objects.equals(password, "")) {
            throw new EmptyPassword();
        }

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

    public String getUsername() {
        return username;
    }
}
