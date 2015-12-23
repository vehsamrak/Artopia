package artopia.services;

import artopia.models.User;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Rottenwood
 */
public class UserServiceTest extends Assert {

    @Test
    public void login_usernameAndPassword_returnsAuthenticatedUser() {
        User user = UserService.login("tester", "password");

        assertTrue(user.isAuthenticated());
    }
}