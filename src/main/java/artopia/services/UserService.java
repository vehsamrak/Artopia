package artopia.services;

import artopia.models.User;

/**
 * @author Rottenwood
 */
public class UserService {

    public static User login(String username, String password) {
        User user = new User(username, password);
        user.authenticate();

        return user;
    }
}
