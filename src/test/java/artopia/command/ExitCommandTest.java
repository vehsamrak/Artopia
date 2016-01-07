package artopia.command;

import artopia.entitiy.User;
import artopia.service.DatabaseService;
import artopia.service.command.CommandResult;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Vehsamrak
 */
public class ExitCommandTest extends Assert
{

    @Test
    public void execute_noParameters_returnsResponseWithLeaveMessageAndExitSubcommand()
    {
        DatabaseService databaseServiceMock = mock(DatabaseService.class);
        when(databaseServiceMock.openSession()).thenReturn(mock(Session.class));

        CommandResult commandResult = new ExitCommand().execute(mock(User.class), databaseServiceMock);
        ArrayList<String> subCommands = commandResult.getSubCommands();

        assertFalse(commandResult.haveErrors());
        assertEquals("До встречи!", commandResult.toString());
        assertTrue(commandResult.haveSubCommands());
        assertEquals(1, subCommands.size());
        assertEquals("exit", subCommands.get(0));
    }
}