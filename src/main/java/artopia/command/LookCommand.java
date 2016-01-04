package artopia.command;

import artopia.command.infrastructure.AbstractCommand;
import artopia.entitiy.User;
import artopia.service.command.CommandResult;

/**
 * @author Rottenwood
 */
public class LookCommand extends AbstractCommand
{

    @Override
    public CommandResult execute(User user)
    {
        String commandResult = "Ты осмотрелся.";

        return new CommandResult("look", commandResult);
    }

    @Override
    public String getDescription()
    {
        return "посмотреть вокруг";
    }
}
