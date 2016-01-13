package artopia.command.infrastructure.open;

import artopia.command.infrastructure.AbstractCommand;
import artopia.command.infrastructure.move.Direction;
import artopia.entitiy.User;
import artopia.entitiy.room.Exit;
import artopia.entitiy.room.Room;
import artopia.exception.ServiceNotFound;
import artopia.exception.UndefinedDirection;
import artopia.service.locator.Service;
import artopia.service.locator.ServiceLocator;
import artopia.service.room.RoomRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Vehsamrak
 */
abstract public class AbstractOpenCommand extends AbstractCommand
{
    private HashMap<Direction, String[]> getDirectionMap()
    {
        HashMap<Direction, String[]> directionMap = new HashMap<>();
        directionMap.put(Direction.NORTH, new String[]{"north", "север"});
        directionMap.put(Direction.EAST, new String[]{"east", "восток"});
        directionMap.put(Direction.SOUTH, new String[]{"south", "юг"});
        directionMap.put(Direction.WEST, new String[]{"west", "запад"});
        directionMap.put(Direction.UP, new String[]{"up", "вверх", "верх", "наверх"});
        directionMap.put(Direction.DOWN, new String[]{"down", "вниз", "низ"});

        return directionMap;
    }

    public Exit getExitByArguments(
            String[] arguments,
            User user,
            ServiceLocator serviceLocator
    ) throws ServiceNotFound, UndefinedDirection
    {
        RoomRepository roomRepository = (RoomRepository) serviceLocator.get(Service.ROOM_REPOSITORY);
        Room currentRoom = roomRepository.findById(user.getRoomId());
        Direction direction = this.findDirectionByFirstLetters(arguments[0]);

        return currentRoom.getExitByDirection(direction);
    }

    protected Direction findDirectionByFirstLetters(String directionString)
    {
        directionString = directionString.toLowerCase();

        for (Map.Entry<Direction, String[]> entry : this.getDirectionMap().entrySet()) {
            Direction direction = entry.getKey();
            String[] directionAliases = entry.getValue();

            for (String directionAlias : directionAliases) {
                if (directionAlias.startsWith(directionString)) {
                    return direction;
                }
            }
        }

        return null;
    }

    protected String getCyrillicDirectionString(Direction direction) throws UndefinedDirection
    {
        String directionString;

        if (direction.equals(Direction.NORTH)) {
            directionString = "на север";
        } else if (direction.equals(Direction.EAST)) {
            directionString = "на восток";
        } else if (direction.equals(Direction.SOUTH)) {
            directionString = "на юг";
        } else if (direction.equals(Direction.WEST)) {
            directionString = "на запад";
        } else if (direction.equals(Direction.UP)) {
            directionString = "наверх";
        } else if (direction.equals(Direction.DOWN)) {
            directionString = "вниз";
        } else {
            throw new UndefinedDirection();
        }

        return directionString;
    }
}
