package artopia.command;

import artopia.entitiy.room.Room;
import artopia.entitiy.User;
import artopia.exception.EmptyPassword;
import artopia.exception.EmptyUsername;
import artopia.exception.ServiceNotFound;
import artopia.service.locator.Service;
import artopia.service.locator.ServiceLocator;
import artopia.service.room.RoomRepository;
import org.junit.Assert;
import org.junit.Test;

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
        assertEquals("test-north", user.getRoomId());
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
        Room centerRoom = new Room(
                "test-center",
                "Center",
                "Center test room",
                "test-north",
                "test-east",
                "test-south",
                "test-west",
                "test-up",
                "test-down"
        );

        Room northRoom = new Room(
                "test-north",
                "North",
                "Northern test room",
                null,
                null,
                "test-center",
                null,
                null,
                null
        );

        RoomRepository roomRepository = mock(RoomRepository.class);
        when(roomRepository.findById("test-center")).thenReturn(centerRoom);
        when(roomRepository.findById("test-north")).thenReturn(northRoom);

        return roomRepository;
    }

    private User createUser() throws EmptyPassword, EmptyUsername
    {
        User user = new User("Tester", "password");
        user.setRoomId("test-center");

        return user;
    }
}
