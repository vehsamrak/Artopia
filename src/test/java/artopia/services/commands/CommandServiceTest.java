package artopia.services.commands;

import artopia.exceptions.EmptyPassword;
import artopia.exceptions.EmptyUsername;
import artopia.models.User;
import artopia.services.commands.errors.CommandNotFound;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Rottenwood
 */
public class CommandServiceTest extends Assert {

    @Test
    public void execute_givenNonexistingCommand_returnCommandResultWithNoSuchCommandError() {
        CommandService commandService = new CommandService(this.createUser());

        String command = "unexistingCommandForTest";
        CommandResult commandResult = commandService.execute(command);

        assertTrue(commandResult.haveErrors());
        assertEquals(CommandNotFound.class, commandResult.getErrors().get(0).getClass());
    }

    private User createUser() {
        try {
            return new User("tester", "password");
        } catch (EmptyUsername | EmptyPassword exception) {
            exception.printStackTrace();
            return null;
        }
    }

    @Test
    public void execute_givenHelpCommand_returnCommandResultWithEqualCommandName() {
        CommandService commandService = new CommandService(this.createUser());

        String command = "help";
        CommandResult commandResult = commandService.execute(command);

        assertFalse(commandResult.haveErrors());
        assertEquals(commandResult.getCommandName(), command);
    }
}
