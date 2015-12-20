package artopia.services;

import artopia.services.command.CommandResult;
import artopia.services.command.CommandService;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Rottenwood
 */
public class CommandServiceTest extends Assert {

    @Test
    public void execute_givenHelpCommand_returnCommandResultWithEqualCommandName() {
        CommandService commandService = new CommandService();

        String command = "help";
        CommandResult commandResult = commandService.execute(command);

        assertEquals(commandResult.command, command);
    }
}
