package artopia.command;

import artopia.command.infrastructure.AbstractCommand;
import artopia.entitiy.Room;
import artopia.entitiy.User;
import artopia.exception.ServiceNotFound;
import artopia.service.command.CommandResult;
import artopia.service.command.errors.CannotMoveThere;
import artopia.service.locator.Service;
import artopia.service.locator.ServiceLocator;
import artopia.service.room.RoomRepository;

/**
 * @author Vehsamrak
 */
public class NorthCommand extends AbstractCommand
{
    @Override
    public String getDescription()
    {
        return "переместиться на север";
    }

    @Override
    public CommandResult execute(
            User user,
            ServiceLocator serviceLocator
    ) throws ServiceNotFound
    {
        CommandResult commandResult = new CommandResult("north");
        RoomRepository roomRepository = (RoomRepository) serviceLocator.get(Service.ROOM_REPOSITORY);
        Room currentRoom = roomRepository.findById(user.getRoomId());

        if (currentRoom == null || currentRoom.getNorth() == null) {
            return createResultWithError(commandResult);
        }

        Room destinationRoom = roomRepository.findById(currentRoom.getNorth());

        if (destinationRoom == null) {
            return createResultWithError(commandResult);
        }

        user.setRoomId(destinationRoom.getId());

        return commandResult;
    }

    private CommandResult createResultWithError(CommandResult commandResult)
    {
        commandResult.addError(new CannotMoveThere());

        return commandResult;
    }
}
