package artopia.services;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Rottenwood
 */
public class CommandServiceTest extends Assert {

    @Test
    public void execute_givenHelpCommandInLowercase_callHelpCommandAndReturnResult() {
        CommandService commandService = new CommandService();

        String command = "help";
        String commandResult = commandService.execute(command);

        assertEquals(commandResult, command);
    }
}
