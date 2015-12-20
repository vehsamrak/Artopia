package artopia.services;

import artopia.services.commands.CommandResult;
import artopia.services.commands.CommandService;
import artopia.services.commands.errors.CommandNotFound;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Rottenwood
 */
public class CommandServiceTest extends Assert {

    @Test
    public void execute_givenNonexistingCommand_returnCommandResultWithNoSuchCommandError() {
        CommandService commandService = new CommandService();

        String command = "unexistingCommandForTest";
        CommandResult commandResult = commandService.execute(command);

        assertTrue(commandResult.haveErrors());
        assertEquals(CommandNotFound.class, commandResult.getErrors().get(0).getClass());
    }

    @Test
    public void execute_givenHelpCommand_returnCommandResultWithEqualCommandName() {
        CommandService commandService = new CommandService();

        String command = "help";
        CommandResult commandResult = commandService.execute(command);

        assertFalse(commandResult.haveErrors());
        assertEquals(commandResult.command, command);
    }
}
