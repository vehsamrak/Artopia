package artopia.command;

import artopia.entitiy.User;
import artopia.exception.ServiceNotFound;
import artopia.service.command.CommandRepository;
import artopia.service.command.CommandResult;
import artopia.service.locator.Service;
import artopia.service.locator.ServiceLocator;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Rottenwood
 */
public class HelpCommandTest extends Assert
{

    @Test
    public void execute_noParameters_returnsHelpMessage() throws ServiceNotFound
    {
        String[] arguments = {};

        CommandResult commandResult = new HelpCommand().execute(arguments, mock(User.class), this.createServiceLocator());

        assertFalse(commandResult.haveErrors());
        assertEquals(commandResult.getCommandName(), "help");
        assertTrue(commandResult.toString().equals("test passed"));
    }

    private ServiceLocator createServiceLocator() throws ServiceNotFound
    {
        CommandRepository commandRepository = mock(CommandRepository.class);
        when(commandRepository.getDescriptions()).thenReturn("test passed");

        ServiceLocator serviceLocator = mock(ServiceLocator.class);
        when(serviceLocator.get(Service.COMMAND_REPOSITORY)).thenReturn(commandRepository);

        return serviceLocator;
    }
}
