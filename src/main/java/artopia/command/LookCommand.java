package artopia.command;

import artopia.command.infrastructure.AbstractCommand;
import artopia.entitiy.Room;
import artopia.entitiy.User;
import artopia.exception.ServiceNotFound;
import artopia.service.command.CommandResult;
import artopia.service.locator.ServiceLocator;
import artopia.service.room.RoomRepository;

/**
 * @author Rottenwood
 */
public class LookCommand extends AbstractCommand
{

    @Override
    public CommandResult execute(User user, ServiceLocator serviceLocator) throws ServiceNotFound
    {
        RoomRepository roomService = (RoomRepository) serviceLocator.get("RoomRepository");
        Room room = roomService.findById(user.getRoomId());

        String commandResult = room.getName() + "\n" + room.getDescription();

        return new CommandResult("look", commandResult);
    }

    @Override
    public String getDescription()
    {
        return "посмотреть вокруг";
    }
}
