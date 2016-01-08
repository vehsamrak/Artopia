package artopia.service.command;

import artopia.service.command.errors.CommandEmpty;
import artopia.service.command.errors.CommandNotFound;
import artopia.service.locator.ServiceLocator;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;

/**
 * @author Rottenwood
 */
public class CommandServiceTest extends Assert
{

    @Test
    public void execute_givenEmptyCommand_returnCommandResultWithEmptyCommandError() throws Exception
    {
        CommandService commandService = createCommandService();

        CommandResult commandResult = commandService.execute("");

        assertTrue(commandResult.haveErrors());
        assertEquals(CommandEmpty.class, commandResult.getErrors().get(0).getClass());
    }

    @Test
    public void execute_givenNonexistingCommand_returnCommandResultWithNoSuchCommandError() throws Exception
    {
        CommandService commandService = createCommandService();

        CommandResult commandResult = commandService.execute("unexistingCommandForTest");

        assertTrue(commandResult.haveErrors());
        assertEquals(CommandNotFound.class, commandResult.getErrors().get(0).getClass());
    }

    @Test
    public void execute_givenAuthorsCommand_returnCommandResultWithEqualCommandName() throws Exception
    {
        CommandService commandService = createCommandService();

        CommandResult commandResult = commandService.execute("authors");

        assertFalse(commandResult.haveErrors());
        assertEquals(commandResult.getCommandName(), "authors");
    }

    private CommandService createCommandService()
    {
        return new CommandService(mock(ServiceLocator.class));
    }
}
