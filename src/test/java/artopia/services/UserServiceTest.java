package artopia.services;

import artopia.exceptions.EmptyPassword;
import artopia.exceptions.EmptyUsername;
import artopia.exceptions.WrongPassword;
import artopia.models.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.*;

/**
 * @author Rottenwood
 */
public class UserServiceTest extends Assert
{

    @Test
    public void login_givenUserServiceWithExistingUser_returnsAuthenticatedUser() {

        UserService userService = this.createUserServiceWithUserTester();

        try {
            User user = userService.login("tester", "password");
            assertTrue(user.isAuthenticated());
        } catch (EmptyPassword | EmptyUsername | WrongPassword exception) {
            exception.printStackTrace();
            assertTrue(false);
        }

    }

    private UserService createUserServiceWithUserTester() {
        DatabaseService databaseService = mock(DatabaseService.class);

        User mockedUser = createUser();
        Query mockedQuery = createQuery(mockedUser);
        Session mockedSession = createSession(mockedQuery);

        when(databaseService.getSession()).thenReturn(mockedSession);

        return new UserService(databaseService);
    }

    private Session createSession(Query mockedQuery) {
        Session mockedSession = mock(Session.class);
        when(mockedSession.createQuery(anyString())).thenReturn(mockedQuery);

        return mockedSession;
    }

    private Query createQuery(User mockedUser) {
        Query mockedQuery = mock(Query.class);
        when(mockedQuery.uniqueResult()).thenReturn(mockedUser);

        return mockedQuery;
    }

    private User createUser() {
        User mockedUser = spy(User.class);
        when(mockedUser.isAuthenticated()).thenReturn(true);
        when(mockedUser.getPassword()).thenReturn("password");

        return mockedUser;
    }
}