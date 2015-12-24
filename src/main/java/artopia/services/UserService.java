package artopia.services;

import artopia.exceptions.EmptyPassword;
import artopia.exceptions.EmptyUsername;
import artopia.models.User;

/**
 * @author Rottenwood
 */
public class UserService {

    public static User login(String username, String password) throws EmptyPassword, EmptyUsername {
        User user = new User(username, password);
        user.authenticate();

        return user;
    }
}
