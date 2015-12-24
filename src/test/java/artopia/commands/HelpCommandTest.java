package artopia.commands;

import artopia.exceptions.EmptyPassword;
import artopia.exceptions.EmptyUsername;
import artopia.models.User;
import artopia.services.commands.CommandResult;
import artopia.services.commands.CommandService;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Rottenwood
 */
public class HelpCommandTest extends Assert {

    @Test
    public void execute_noParameters_returnsHelpMessage() {
        User user = null;
        try {
            user = new User("tester", "password");
        } catch (EmptyUsername | EmptyPassword exception) {
            exception.printStackTrace();
            assertTrue(false);
        }

        CommandService commandService = new CommandService(user);

        String command = "help";
        CommandResult commandResult = commandService.execute(command);

        String helpCommandResultString = "" +
                "Доступные команды:\n" +
                "help - игровая информация\n" +
                "look - посмотреть вокруг";

        assertFalse(commandResult.haveErrors());
        assertEquals(commandResult.getCommandName(), command);
        assertEquals(commandResult.toString(), helpCommandResultString);
    }
}
