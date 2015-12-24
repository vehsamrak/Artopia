package artopia.services;

import artopia.exceptions.EmptyPassword;
import artopia.exceptions.EmptyUsername;
import artopia.models.User;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Rottenwood
 */
public class UserServiceTest extends Assert {

    @Test
    public void login_usernameAndPassword_returnsAuthenticatedUser() {
        String username = "tester";
        User user = null;
        try {
            user = UserService.login(username, "password");
            assertTrue(user.isAuthenticated());
            assertEquals(username, user.getUsername());
        } catch (EmptyPassword | EmptyUsername exception) {
            exception.printStackTrace();
            assertTrue(false);
        }

    }
}