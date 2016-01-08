package artopia.command;

import artopia.entitiy.Room;
import artopia.entitiy.User;
import artopia.exception.ServiceNotFound;
import artopia.service.command.CommandResult;
import artopia.service.locator.ServiceLocator;
import artopia.service.room.RoomRepository;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Vehsamrak
 */
public class LookCommandTest extends Assert
{

    @Test
    public void execute_userWithRoom_returnsResponseContainingRoomNameAndDescription() throws ServiceNotFound
    {
        Room room = this.createRoom();

        RoomRepository roomRepository = mock(RoomRepository.class);
        when(roomRepository.findById("test-room-id")).thenReturn(room);

        ServiceLocator serviceLocator = mock(ServiceLocator.class);
        when(serviceLocator.get("RoomRepository")).thenReturn(roomRepository);

        CommandResult commandResult = new LookCommand().execute(this.createUser(), serviceLocator);

        assertFalse(commandResult.haveErrors());
        assertEquals("Test room\nTest room description.", commandResult.toString());
    }

    private User createUser()
    {
        User user = mock(User.class);
        when(user.getRoomId()).thenReturn("test-room-id");

        return user;
    }

    private Room createRoom()
    {
        Room room = mock(Room.class);
        when(room.getId()).thenReturn("test-room-id");
        when(room.getName()).thenReturn("Test room");
        when(room.getDescription()).thenReturn("Test room description.");

        return room;
    }
}