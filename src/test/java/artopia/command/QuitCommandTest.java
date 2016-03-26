package artopia.command;

import artopia.entitiy.User;
import artopia.exception.ServiceNotFound;
import artopia.service.DatabaseService;
import artopia.service.command.CommandResult;
import artopia.service.locator.Service;
import artopia.service.locator.ServiceLocator;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Vehsamrak
 */
public class QuitCommandTest extends Assert
{

    @Test
    public void execute_noParameters_returnsResponseWithLeaveMessageAndExitSubcommand() throws ServiceNotFound
    {

        String[] arguments = {};
        ServiceLocator serviceLocator = createServiceLocator();
        CommandResult commandResult = new QuitCommand().execute(arguments, mock(User.class), serviceLocator);
        ArrayList<String> subCommands = commandResult.getSubCommands();

        assertFalse(commandResult.haveErrors());
        assertEquals("До встречи!", commandResult.toString());
        assertTrue(commandResult.haveSubCommands());
        assertEquals(1, subCommands.size());
        assertEquals("exit", subCommands.get(0));
    }

    private ServiceLocator createServiceLocator() throws ServiceNotFound{
        Transaction transaction = mock(Transaction.class);

        Session session = mock(Session.class);
        when(session.beginTransaction()).thenReturn(transaction);

        DatabaseService databaseServiceMock = mock(DatabaseService.class);
        when(databaseServiceMock.openSession()).thenReturn(session);

        ServiceLocator serviceLocator = mock(ServiceLocator.class);
        when(serviceLocator.get(Service.DATABASE)).thenReturn(databaseServiceMock);

      return  serviceLocator;
    }
}