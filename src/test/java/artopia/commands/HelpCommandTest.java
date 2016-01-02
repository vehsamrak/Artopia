package artopia.commands;

import artopia.models.User;
import artopia.services.commands.CommandResult;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;

/**
 * @author Rottenwood
 */
public class HelpCommandTest extends Assert
{

    @Test
    public void execute_noParameters_returnsHelpMessage()
    {
        CommandResult commandResult = new HelpCommand().execute(mock(User.class));

        assertFalse(commandResult.haveErrors());
        assertEquals(commandResult.getCommandName(), "help");
        assertTrue(commandResult.toString().startsWith("Игровые команды\n===============\n" +
                "authors, credits, авторы - информация об авторах проекта\n"));
        assertTrue(commandResult.toString().contains("\nexit, quit, выход - выход из игры\n"));
    }
}
