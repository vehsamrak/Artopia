package artopia.command;

import artopia.command.infrastructure.move.AbstractDirectionCommand;
import artopia.command.infrastructure.move.Direction;
import artopia.entitiy.User;
import artopia.service.command.CommandResult;
import artopia.service.locator.ServiceLocator;

/**
 * @author Vehsamrak
 */
public class WestCommand extends AbstractDirectionCommand
{
    @Override
    public String getDescription()
    {
        return "переместиться на запад";
    }

    @Override
    public CommandResult execute(
            String[] arguments,
            User user,
            ServiceLocator serviceLocator
    ) throws Exception
    {
        return super.moveToDirection(Direction.WEST, user, serviceLocator);
    }
}
