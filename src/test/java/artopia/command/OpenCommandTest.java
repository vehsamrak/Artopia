package artopia.command;

import artopia.entitiy.User;
import artopia.entitiy.room.Exit;
import artopia.entitiy.room.Room;
import artopia.exception.UndefinedDirection;
import artopia.service.command.CommandResult;
import artopia.service.command.errors.ArgumentMissing;
import artopia.service.command.errors.OpenTargetNotFound;
import artopia.service.locator.Service;
import artopia.service.locator.ServiceLocator;
import artopia.service.room.RoomRepository;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Vehsamrak
 */
public class OpenCommandTest extends Assert
{
    @Test
    public void execute_givenEmptyArgument_returnsResultWithError() throws Exception
    {
        String[] arguments = new String[]{};
        User user = this.createUser();
        ServiceLocator serviceLocator = this.createServiceLocatorWithoutExits();

        OpenCommand openCommand = new OpenCommand();
        CommandResult result = openCommand.execute(arguments, user, serviceLocator);

        assertTrue(result.haveErrors());
        assertEquals(1, result.getErrors().size());
        assertTrue(result.getErrors().get(0) instanceof ArgumentMissing);
        assertEquals("Что именно открыть?", result.getErrors().get(0).getErrorMessage());
    }

    @Test
    public void execute_givenArgumentNorthDirectionAndUserInRoomWithoutExits_returnsResultWithError() throws Exception
    {
        String[] arguments = new String[]{"north"};
        User user = this.createUser();
        ServiceLocator serviceLocator = this.createServiceLocatorWithoutExits();

        OpenCommand openCommand = new OpenCommand();
        CommandResult result = openCommand.execute(arguments, user, serviceLocator);

        assertTrue(result.haveErrors());
        assertEquals(1, result.getErrors().size());
        assertTrue(result.getErrors().get(0) instanceof OpenTargetNotFound);
        assertEquals("Ты не нашел того что хотел открыть.", result.getErrors().get(0).getErrorMessage());
    }

    @Test
    public void execute_givenArgumentNorthDirectionAndUserInRoomWithOpenedExits_returnsResultWithError() throws Exception
    {
        String[] arguments = new String[]{"north"};
        User user = this.createUser();
        ServiceLocator serviceLocator = this.createServiceLocatorWithOpenedExits();

        OpenCommand openCommand = new OpenCommand();
        CommandResult result = openCommand.execute(arguments, user, serviceLocator);

        assertTrue(result.haveErrors());
        assertEquals(1, result.getErrors().size());
        assertTrue(result.getErrors().get(0) instanceof OpenTargetNotFound);
        assertEquals("Ты не нашел того что хотел открыть.", result.getErrors().get(0).getErrorMessage());
    }

    @Test
    public void execute_givenArgumentNorthDirectionAndUserInRoomWithClosedExits_returnsResultWithSuccess() throws Exception
    {
        String[] arguments = new String[]{"north"};
        User user = this.createUser();
        ServiceLocator serviceLocator = this.createServiceLocatorWithClosedExits();

        OpenCommand openCommand = new OpenCommand();
        CommandResult result = openCommand.execute(arguments, user, serviceLocator);

        assertFalse(result.haveErrors());
        assertEquals("Ты открыл проход на север.", result.toString());
    }

    private User createUser()
    {
        User user = mock(User.class);
        when(user.getRoomId()).thenReturn("center");

        return user;
    }

    private ServiceLocator createServiceLocatorWithOpenedExits() throws Exception
    {
        Room room = this.createRoomWithOpenedExits();
        RoomRepository roomRepository = createRoomRepository(room);

        ServiceLocator serviceLocator = mock(ServiceLocator.class);
        when(serviceLocator.get(Service.ROOM_REPOSITORY)).thenReturn(roomRepository);

        return serviceLocator;
    }

    private ServiceLocator createServiceLocatorWithoutExits() throws Exception
    {
        Room room = this.createRoomWithoutExits();
        RoomRepository roomRepository = createRoomRepository(room);

        ServiceLocator serviceLocator = mock(ServiceLocator.class);
        when(serviceLocator.get(Service.ROOM_REPOSITORY)).thenReturn(roomRepository);

        return serviceLocator;
    }

    private ServiceLocator createServiceLocatorWithClosedExits() throws Exception
    {
        Room room = this.createRoomWithClosedExits();
        RoomRepository roomRepository = createRoomRepository(room);

        ServiceLocator serviceLocator = mock(ServiceLocator.class);
        when(serviceLocator.get(Service.ROOM_REPOSITORY)).thenReturn(roomRepository);

        return serviceLocator;
    }

    private RoomRepository createRoomRepository(Room room)
    {
        String RoomId = room.getId();
        RoomRepository roomRepository = mock(RoomRepository.class);
        when(roomRepository.findById(RoomId)).thenReturn(room);
        return roomRepository;
    }

    private Room createRoomWithOpenedExits() throws UndefinedDirection
    {
        Exit exit = mock(Exit.class);
        when(exit.isClosed()).thenReturn(false);

        Room room = mock(Room.class);
        when(room.getId()).thenReturn("center");
        when(room.getExitByDirection(any())).thenReturn(exit);

        return room;
    }

    private Room createRoomWithClosedExits() throws UndefinedDirection
    {
        Exit exit = mock(Exit.class);
        when(exit.isClosed()).thenReturn(true);

        Room room = mock(Room.class);
        when(room.getId()).thenReturn("center");
        when(room.getExitByDirection(any())).thenReturn(exit);

        return room;
    }

    private Room createRoomWithoutExits() throws UndefinedDirection
    {
        Room room = mock(Room.class);
        when(room.getId()).thenReturn("center");

        return room;
    }
}
