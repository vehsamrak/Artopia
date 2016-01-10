package artopia.command;

import artopia.command.infrastructure.AbstractCommand;
import artopia.command.infrastructure.move.Direction;
import artopia.entitiy.User;
import artopia.entitiy.room.Exit;
import artopia.entitiy.room.Room;
import artopia.exception.UndefinedDirection;
import artopia.service.command.CommandResult;
import artopia.service.command.errors.AbstractCommandError;
import artopia.service.command.errors.OpenTargetNotFound;
import artopia.service.locator.Service;
import artopia.service.locator.ServiceLocator;
import artopia.service.room.RoomRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Vehsamrak
 */
public class OpenCommand extends AbstractCommand
{

    private HashMap<Direction, String[]> directionMap = new HashMap<>();

    public OpenCommand()
    {
        this.directionMap.put(Direction.NORTH, new String[]{"north", "север"});
        this.directionMap.put(Direction.EAST, new String[]{"east", "восток"});
        this.directionMap.put(Direction.SOUTH, new String[]{"south", "юг"});
        this.directionMap.put(Direction.WEST, new String[]{"west", "запад"});
        this.directionMap.put(Direction.UP, new String[]{"up", "вверх", "верх", "наверх"});
        this.directionMap.put(Direction.DOWN, new String[]{"down", "вниз", "низ"});
    }

    @Override
    public String getDescription()
    {
        return "открыть что-либо, будь то сундук или дверь";
    }

    @Override
    public CommandResult execute(
            String[] arguments, User user, ServiceLocator serviceLocator
    ) throws Exception
    {
        RoomRepository roomRepository = (RoomRepository) serviceLocator.get(Service.ROOM_REPOSITORY);
        Room currentRoom = roomRepository.findById(user.getRoomId());
        Direction direction = this.findDirectionByFirstLetters(arguments[0]);

        if (direction == null) {
            return this.createCommandResultWithError(new OpenTargetNotFound());
        }

        Exit exit = currentRoom.getExitByDirection(direction);

        if (exit == null || !exit.isClosed()) {
            return this.createCommandResultWithError(new OpenTargetNotFound());
        }

        exit.open();

        return new CommandResult("open", "Ты открыл проход " + this.getCyrillicDirectionString(direction) + ".");
    }

    private CommandResult createCommandResultWithError(AbstractCommandError error)
    {
        CommandResult commandResult = new CommandResult("open");
        commandResult.addError(error);

        return commandResult;
    }

    private Direction findDirectionByFirstLetters(String directionString)
    {
        directionString = directionString.toLowerCase();

        for (Map.Entry<Direction, String[]> entry : this.directionMap.entrySet()) {
            Direction direction = entry.getKey();
            String[] directionAliases = entry.getValue();

            for (String directionAlias: directionAliases) {
                if (directionAlias.startsWith(directionString)) {
                    return direction;
                }
            }
        }

        return null;
    }

    private String getCyrillicDirectionString(Direction direction) throws UndefinedDirection
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
