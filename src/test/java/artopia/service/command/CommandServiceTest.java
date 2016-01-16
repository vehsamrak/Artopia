package artopia.service.command;

import artopia.entitiy.User;
import artopia.entitiy.room.Room;
import artopia.exception.ServiceNotFound;
import artopia.service.command.errors.CommandEmpty;
import artopia.service.command.errors.CommandNotFound;
import artopia.service.locator.Service;
import artopia.service.locator.ServiceLocator;
import artopia.service.room.RoomRepository;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

    @Test
    public void execute_givenOneLetterDirectionalCommands_returnCommandResultsWithEqualCommandNames() throws Exception
    {
        CommandService commandService = createCommandService();

        assertEquals("north", commandService.execute("n").getCommandName());
        assertEquals("east", commandService.execute("e").getCommandName());
        assertEquals("south", commandService.execute("s").getCommandName());
        assertEquals("west", commandService.execute("w").getCommandName());
        assertEquals("up", commandService.execute("u").getCommandName());
        assertEquals("down", commandService.execute("d").getCommandName());

        assertEquals("north", commandService.execute("с").getCommandName());
        assertEquals("east", commandService.execute("в").getCommandName());
        assertEquals("south", commandService.execute("ю").getCommandName());
        assertEquals("west", commandService.execute("з").getCommandName());
        assertEquals("up", commandService.execute("вв").getCommandName());
        assertEquals("down", commandService.execute("вн").getCommandName());
        assertEquals("down", commandService.execute("н").getCommandName());
    }

    private CommandService createCommandService() throws ServiceNotFound
    {
        User user = mock(User.class);
        when(user.getRoomId()).thenReturn("center");

        Room room = mock(Room.class);
        when(room.getId()).thenReturn("center");

        RoomRepository roomRepository = mock(RoomRepository.class);
        when(roomRepository.findById(anyString())).thenReturn(room);

        ServiceLocator serviceLocator = mock(ServiceLocator.class);
        when(serviceLocator.get(Service.ROOM_REPOSITORY)).thenReturn(roomRepository);

        CommandService commandService = new CommandService(serviceLocator);
        commandService.setUser(user);

        return commandService;
    }
}
