package artopia.command;

import artopia.entitiy.User;
import artopia.service.command.CommandResult;
import artopia.service.locator.ServiceLocator;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;

/**
 * @author Vehsamrak
 */
public class AuthorsCommandTest extends Assert
{
    @Test
    public void execute_noParameters_returnsCommandResultWithAuthors()
    {
        String[] arguments = {};
        CommandResult commandResult = new AuthorsCommand().execute(arguments, mock(User.class), mock(ServiceLocator.class));

        assertFalse(commandResult.haveErrors());
        assertEquals("Автор проекта: Petr Karmashev (Vehsamrak)", commandResult.toString());
    }
}
