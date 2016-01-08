package artopia.command;

import artopia.entitiy.Room;
import artopia.entitiy.User;
import artopia.exception.EmptyPassword;
import artopia.exception.EmptyUsername;
import artopia.exception.ServiceNotFound;
import artopia.service.locator.Service;
import artopia.service.locator.ServiceLocator;
import artopia.service.room.RoomRepository;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Vehsamrak
 */
public class NorthCommandTest extends Assert
{

    @Test
    public void execute_userInRoom_userMovedToAnotherRoom() throws Exception
    {
        User user = this.createUser();
        String currentRoomId = user.getRoomId();

        NorthCommand northCommand = new NorthCommand();
        northCommand.execute(user, this.createServiceLocator());

        assertNotEquals(currentRoomId, user.getRoomId());
        assertEquals("northern-room", user.getRoomId());
    }

    private ServiceLocator createServiceLocator() throws ServiceNotFound
    {
        RoomRepository roomRepository = createRoomRepository();

        ServiceLocator serviceLocator = mock(ServiceLocator.class);
        when(serviceLocator.get(Service.ROOM_REPOSITORY)).thenReturn(roomRepository);

        return serviceLocator;
    }

    private RoomRepository createRoomRepository()
    {
        Room room = mock(Room.class);
        when(room.getNorth()).thenReturn("test-northern");

        RoomRepository roomRepository = mock(RoomRepository.class);
        when(roomRepository.findById(anyString())).thenReturn(room);

        return roomRepository;
    }

    private User createUser() throws EmptyPassword, EmptyUsername
    {
        return new User("Tester", "password");
    }
}
