package artopia.command.infrastructure.open;

import artopia.entitiy.User;
import artopia.entitiy.room.Exit;
import artopia.entitiy.room.Room;
import artopia.exception.ServiceNotFound;
import artopia.service.command.CommandResult;
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
public class AbstractOpenCommandTest extends Assert
{

    @Test
    public void getExitByArguments_commandArgumentNorth_returnsExitToNort() throws Exception
    {
        AbstractOpenCommand concreteOpenCommand = this.createConcreteOpenCommand();
        String[] arguments = new String[]{"north"};
        User user = this.createUser();
        ServiceLocator serviceLocator = this.createServiceLocator();

        Exit exitNorth = concreteOpenCommand.getExitByArguments(arguments, user, serviceLocator);

        assertEquals(exitNorth.getRoomId(), "north-room");
    }

    private ServiceLocator createServiceLocator() throws ServiceNotFound
    {
        Room room = new Room(
                "center-room",
                "Center room",
                "Center room description",
                new Exit("north-room"),
                new Exit("east-room"),
                new Exit("south-room"),
                new Exit("west-room"),
                new Exit("up-room"),
                new Exit("down-room")
        );

        RoomRepository roomRepository = mock(RoomRepository.class);
        when(roomRepository.findById("center-room")).thenReturn(room);

        ServiceLocator serviceLocator = mock(ServiceLocator.class);
        when(serviceLocator.get(Service.ROOM_REPOSITORY)).thenReturn(roomRepository);

        return serviceLocator;
    }

    private User createUser()
    {
        User user = mock(User.class);
        when(user.getRoomId()).thenReturn("center-room");

        return user;
    }

    private AbstractOpenCommand createConcreteOpenCommand()
    {
        return new AbstractOpenCommand()
        {
            @Override
            public String getDescription()
            {
                return null;
            }

            @Override
            public CommandResult execute(
                    String[] arguments, User user, ServiceLocator serviceLocator
            ) throws Exception
            {
                return null;
            }
        };
    }
}