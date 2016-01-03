package artopia.command;

import artopia.entitiy.User;
import artopia.service.commands.CommandResult;
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
        CommandResult commandResult = new AuthorsCommand().execute(mock(User.class));

        assertFalse(commandResult.haveErrors());
        assertEquals("Автор проекта: Petr Karmashev (Vehsamrak)", commandResult.toString());
    }
}
