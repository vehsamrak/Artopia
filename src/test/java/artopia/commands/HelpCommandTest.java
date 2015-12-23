package artopia.commands;

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
        CommandService commandService = new CommandService(new User("tester", "password"));

        String command = "help";
        CommandResult commandResult = commandService.execute(command);

        String helpCommandResultString = "" +
                "Доступные команды:\n" +
                "help - игровая информация\n" +
                "look - посмотреть вокруг\n";

        assertFalse(commandResult.haveErrors());
        assertEquals(commandResult.getCommandName(), command);
        assertEquals(commandResult.toString(), helpCommandResultString);
    }
}
