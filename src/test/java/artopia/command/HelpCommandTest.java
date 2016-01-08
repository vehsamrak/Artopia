package artopia.command;

import artopia.entitiy.User;
import artopia.service.command.CommandResult;
import artopia.service.locator.ServiceLocator;
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
        CommandResult commandResult = new HelpCommand().execute(mock(User.class), mock(ServiceLocator.class));

        assertFalse(commandResult.haveErrors());
        assertEquals(commandResult.getCommandName(), "help");
        assertTrue(commandResult.toString().startsWith("Игровые команды\n===============\n" +
                "authors, credits, авторы - информация об авторах проекта\n"));
        assertTrue(commandResult.toString().contains("\nexit, quit, выход - выход из игры\n"));
    }
}
