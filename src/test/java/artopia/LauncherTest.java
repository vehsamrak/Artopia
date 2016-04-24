package artopia;

import artopia.exception.ServiceNotFound;
import artopia.service.DatabaseService;
import artopia.service.locator.Service;
import artopia.service.locator.ServiceLocator;

import org.hibernate.Session;
import org.hibernate.Transaction;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Rule;
import org.junit.Assert;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author wooferclaw
 */
public class LauncherTest extends Assert
{

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUpStreams() throws Exception
    {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void cleanUpStreams()
    {
        System.setOut(null);
        System.setErr(null);
    }

    @Test
    public void checkMainConsoleOutput() throws Exception
    {

        ServiceLocator serviceLocator = createServiceLocator();
        Launcher launcher = new Launcher(serviceLocator);

        launcher.run();
        assertEquals("Инициализация приложения ...\r\n", outContent.toString());
    }

    private ServiceLocator createServiceLocator() throws ServiceNotFound
    {
        Transaction transaction = mock(Transaction.class);

        Session session = mock(Session.class);
        when(session.beginTransaction()).thenReturn(transaction);

        DatabaseService databaseServiceMock = mock(DatabaseService.class);
        when(databaseServiceMock.openSession()).thenReturn(session);

        ServiceLocator serviceLocator = mock(ServiceLocator.class);
        when(serviceLocator.get(Service.DATABASE)).thenReturn(databaseServiceMock);

        return serviceLocator;
    }
}



