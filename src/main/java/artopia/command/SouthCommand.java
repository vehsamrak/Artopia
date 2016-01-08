package artopia.command;

import artopia.command.infrastructure.move.AbstractDirectionCommand;
import artopia.command.infrastructure.move.Direction;
import artopia.entitiy.User;
import artopia.service.command.CommandResult;
import artopia.service.locator.ServiceLocator;

/**
 * @author Vehsamrak
 */
public class SouthCommand extends AbstractDirectionCommand
{
    @Override
    public String getDescription()
    {
        return "переместиться на юг";
    }

    @Override
    public CommandResult execute(
            User user,
            ServiceLocator serviceLocator
    ) throws Exception
    {
        return super.moveToDirection(Direction.SOUTH, user, serviceLocator);
    }
}
