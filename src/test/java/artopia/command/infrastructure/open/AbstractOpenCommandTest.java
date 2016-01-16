package artopia.command.infrastructure.open;

import artopia.command.infrastructure.move.Direction;
import artopia.entitiy.User;
import artopia.entitiy.room.Exit;
import artopia.entitiy.room.Room;
import artopia.exception.ServiceNotFound;
import artopia.exception.UndefinedDirection;
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
        User user = this.createUser();
        ServiceLocator serviceLocator = this.createServiceLocator();

        Exit exitNorth = concreteOpenCommand.getExitByArguments(new String[]{"north"}, user, serviceLocator);
        Exit exitEast = concreteOpenCommand.getExitByArguments(new String[]{"east"}, user, serviceLocator);
        Exit exitSouth = concreteOpenCommand.getExitByArguments(new String[]{"south"}, user, serviceLocator);
        Exit exitWest = concreteOpenCommand.getExitByArguments(new String[]{"west"}, user, serviceLocator);
        Exit exitUp = concreteOpenCommand.getExitByArguments(new String[]{"up"}, user, serviceLocator);
        Exit exitDown = concreteOpenCommand.getExitByArguments(new String[]{"down"}, user, serviceLocator);

        assertEquals(exitNorth.getRoomId(), "north-room");
        assertEquals(exitEast.getRoomId(), "east-room");
        assertEquals(exitSouth.getRoomId(), "south-room");
        assertEquals(exitWest.getRoomId(), "west-room");
        assertEquals(exitUp.getRoomId(), "up-room");
        assertEquals(exitDown.getRoomId(), "down-room");
    }

    @Test(expected = UndefinedDirection.class)
    public void getExitByArguments_commandArgumentUnknownExit_throwsUndefinedDirection() throws Exception
    {
        AbstractOpenCommand concreteOpenCommand = this.createConcreteOpenCommand();
        User user = this.createUser();
        ServiceLocator serviceLocator = this.createServiceLocator();

        concreteOpenCommand.getExitByArguments(new String[]{"unknown"}, user, serviceLocator);
    }

    @Test
    public void getCyrillicDirectionString_givenDirections_returnsTheirCyrillicNames() throws Exception
    {
        AbstractOpenCommand concreteOpenCommand = this.createConcreteOpenCommand();

        String northDirectionString = concreteOpenCommand.getCyrillicDirectionString(Direction.NORTH);
        String eastDirectionString = concreteOpenCommand.getCyrillicDirectionString(Direction.EAST);
        String southDirectionString = concreteOpenCommand.getCyrillicDirectionString(Direction.SOUTH);
        String westDirectionString = concreteOpenCommand.getCyrillicDirectionString(Direction.WEST);
        String upDirectionString = concreteOpenCommand.getCyrillicDirectionString(Direction.UP);
        String downDirectionString = concreteOpenCommand.getCyrillicDirectionString(Direction.DOWN);

        assertEquals("на север", northDirectionString);
        assertEquals("на восток", eastDirectionString);
        assertEquals("на юг", southDirectionString);
        assertEquals("на запад", westDirectionString);
        assertEquals("наверх", upDirectionString);
        assertEquals("вниз", downDirectionString);
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

            @Override
            public String getCyrillicDirectionString(Direction direction) throws UndefinedDirection
            {
                return super.getCyrillicDirectionString(direction);
            }
        };
    }
}
