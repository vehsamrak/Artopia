package artopia.command.infrastructure.move;

import artopia.command.infrastructure.AbstractCommand;
import artopia.entitiy.room.Room;
import artopia.entitiy.User;
import artopia.service.command.CommandResult;
import artopia.service.command.errors.CannotMoveThere;
import artopia.service.locator.Service;
import artopia.service.locator.ServiceLocator;
import artopia.service.room.RoomRepository;

/**
 * @author Vehsamrak
 */
abstract public class AbstractDirectionCommand extends AbstractCommand
{
    private CommandResult createResultWithError(CommandResult commandResult)
    {
        commandResult.addError(new CannotMoveThere());

        return commandResult;
    }

    protected CommandResult moveToDirection(
            Direction direction,
            User user,
            ServiceLocator serviceLocator
    ) throws Exception
    {
        String directionString;

        if (direction.equals(Direction.NORTH)) {
            directionString = "north";
        } else if (direction.equals(Direction.EAST)) {
            directionString = "east";
        } else if (direction.equals(Direction.SOUTH)) {
            directionString = "south";
        } else if (direction.equals(Direction.WEST)) {
            directionString = "west";
        } else if (direction.equals(Direction.UP)) {
            directionString = "up";
        } else if (direction.equals(Direction.DOWN)) {
            directionString = "down";
        } else {
            throw new Exception("Undefined direction");
        }

        CommandResult commandResult = new CommandResult(directionString);

        RoomRepository roomRepository = (RoomRepository) serviceLocator.get(Service.ROOM_REPOSITORY);
        Room destinationRoom = this.getDestinationRoom(user, roomRepository, direction);

        if (destinationRoom == null) {
            return createResultWithError(commandResult);
        }

        user.setRoomId(destinationRoom.getId());

        return commandResult;
    }

    private Room getDestinationRoom(User user, RoomRepository roomRepository, Direction direction) throws Exception
    {
        Room currentRoom = roomRepository.findById(user.getRoomId());

        if (currentRoom == null) {
            return null;
        }

        String directionId;

        if (direction.equals(Direction.NORTH)) {
            directionId = currentRoom.getNorth();
        } else if (direction.equals(Direction.EAST)) {
            directionId = currentRoom.getEast();
        } else if (direction.equals(Direction.SOUTH)) {
            directionId = currentRoom.getSouth();
        } else if (direction.equals(Direction.WEST)) {
            directionId = currentRoom.getWest();
        } else if (direction.equals(Direction.UP)) {
            directionId = currentRoom.getUp();
        } else if (direction.equals(Direction.DOWN)) {
            directionId = currentRoom.getDown();
        } else {
            throw new Exception("Undefined direction");
        }

        if (directionId == null) {
            return null;
        }

        return roomRepository.findById(directionId);
    }
}
