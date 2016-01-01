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
        assertEquals(commandResult.toString(), "" +
                "Доступные команды:\n" +
                "help - игровая информация\n" +
                "look - посмотреть вокруг\n" +
                "exit - выход из игры");
    }
}
