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
        String username = "tester";
        User user = UserService.login(username, "password");

        assertTrue(user.isAuthenticated());
        assertEquals(username, user.getUsername());
    }
}