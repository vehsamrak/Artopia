package artopia.command.infrastructure.move;

import artopia.command.infrastructure.AbstractCommand;
import artopia.entitiy.User;
import artopia.entitiy.room.Exit;
import artopia.entitiy.room.Room;
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
        CommandResult commandResult = new CommandResult(direction.toString());

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

        Exit exit;

        if (direction.equals(Direction.NORTH)) {
            exit = currentRoom.getNorth();
        } else if (direction.equals(Direction.EAST)) {
            exit = currentRoom.getEast();
        } else if (direction.equals(Direction.SOUTH)) {
            exit = currentRoom.getSouth();
        } else if (direction.equals(Direction.WEST)) {
            exit = currentRoom.getWest();
        } else if (direction.equals(Direction.UP)) {
            exit = currentRoom.getUp();
        } else if (direction.equals(Direction.DOWN)) {
            exit = currentRoom.getDown();
        } else {
            throw new Exception("Undefined direction");
        }

        if (exit == null) {
            return null;
        }

        return roomRepository.findById(exit.getRoomId());
    }
}
