package artopia.service;

import artopia.entitiy.User;
import artopia.exception.EmptyPassword;
import artopia.exception.EmptyUsername;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import vehsamrak.helper.NullOutputStream;

import java.io.PrintStream;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Rottenwood
 */
public class UserServiceTest extends Assert
{
    @Before
    public void setUp() throws Exception
    {
        System.setOut(new PrintStream(new NullOutputStream()));
    }

    @Test
    public void login_givenUserServiceWithExistingUser_returnsAuthenticatedUser() throws Exception
    {
        UserService userService = this.createUserServiceWithUserTester();

        User user = userService.login("tester", "password");

        assertTrue(user.isAuthenticated());
    }

    private UserService createUserServiceWithUserTester() throws EmptyPassword, EmptyUsername
    {
        DatabaseService databaseService = mock(DatabaseService.class);
        User mockedUser = createUser();
        Query mockedQuery = createQuery(mockedUser);
        Session mockedSession = createSession(mockedQuery);
        when(databaseService.openSession()).thenReturn(mockedSession);

        return new UserService(databaseService);
    }

    private Session createSession(Query mockedQuery)
    {
        Session mockedSession = mock(Session.class);
        when(mockedSession.createQuery(anyString())).thenReturn(mockedQuery);

        return mockedSession;
    }

    private Query createQuery(User mockedUser)
    {
        Query mockedQuery = mock(Query.class);
        when(mockedQuery.uniqueResult()).thenReturn(mockedUser);

        return mockedQuery;
    }

    private User createUser() throws EmptyPassword, EmptyUsername
    {
        User user = new User("Tester", "password");
        user.authenticate();

        return user;
    }
}